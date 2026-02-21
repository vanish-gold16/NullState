package commands;

import dao.DialogDAO;
import main.CommandManager;
import models.DialogueNode;
import models.DialogueOption;
import models.Interaction;
import models.NPC;
import models.Player;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Talk implements Command{

    private static final Pattern PAYMENT_PATTERN = Pattern.compile("zaplatit\\s*(\\d+)", Pattern.CASE_INSENSITIVE);

    private CommandManager commandManager;
    private DialogDAO dialogDAO;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim();

        if(args.equals("talk")) return "Pouziti: mluv <jmeno_NPC>";

        if(args.isEmpty()) {
            return "Zadej s kym chces mluvit. Pouziti: mluv <jmeno_NPC>";
        }

        String npcName = args.toLowerCase();
        NPC npc = commandManager.getCurrentLocation().getNpcs().stream()
                .filter(n -> n.getName().toLowerCase().equals(npcName))
                .findFirst().orElse(null);

        if(npc == null) {
            return "Tento NPC zde neni.";
        }

        Map<String, DialogueNode> dialogNodes = dialogDAO.getDialogForNPC(npcName);
        if(dialogNodes == null) {
            return npc.randomInteraction(commandManager.getNpcDAO());
        }

        Interaction interaction = new Interaction(dialogNodes);
        interaction.startDialog();

        while(interaction.isActive()){
            System.out.println(interaction.displayCurrentNode());

            if(!interaction.isActive()) break;

            System.out.print(">> ");
            String input = commandManager.getScanner().nextLine().trim();

            try{
                int choice = Integer.parseInt(input);
                DialogueNode currentNode = interaction.getCurrentNode();
                if(choice < 1 || choice > currentNode.getOptions().size()){
                    System.out.println("Neplatna volba.");
                    continue;
                }

                DialogueOption selectedOption = currentNode.getOptions().get(choice - 1);
                int paymentAmount = extractPaymentAmount(selectedOption.getText());

                if(paymentAmount > 0){
                    Player player = commandManager.getPlayer();
                    int currentMoney = player.getEddie();

                    if(currentMoney >= paymentAmount){
                        player.setEddie(currentMoney - paymentAmount);
                        System.out.println("Zaplatil jsi " + paymentAmount + " eddies.");
                        interaction.setCurrentNodeId(selectedOption.getNextDialogNodeId());
                    } else {
                        System.out.println("Nemas dostatek eddies. Potrebujes " + paymentAmount + ".");
                        interaction.setCurrentNodeId(resolveFallbackNodeId(dialogNodes));
                    }
                    continue;
                }

                interaction.setCurrentNodeId(selectedOption.getNextDialogNodeId());
            } catch(NumberFormatException e){
                System.out.println("Zadej cislo odpovedi.");
            }
        }

        return "Konec rozhovoru s " + npc.getName() + ".";
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Talk(CommandManager commandManager, DialogDAO dialogDAO) {
        this.commandManager = commandManager;
        this.dialogDAO = dialogDAO;
    }

    private int extractPaymentAmount(String optionText){
        if(optionText == null){
            return 0;
        }

        Matcher matcher = PAYMENT_PATTERN.matcher(optionText);
        if(!matcher.find()){
            return 0;
        }

        try{
            return Integer.parseInt(matcher.group(1));
        } catch (NumberFormatException e){
            return 0;
        }
    }

    private String resolveFallbackNodeId(Map<String, DialogueNode> dialogNodes){
        if(dialogNodes != null && dialogNodes.containsKey("job")){
            return "job";
        }
        return "end";
    }
}
