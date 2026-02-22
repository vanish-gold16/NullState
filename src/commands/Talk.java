package commands;

import dao.DialogDAO;
import dao.ItemDAO;
import main.CommandManager;
import models.DialogueNode;
import models.DialogueOption;
import models.Interaction;
import models.Item;
import models.NPC;
import models.Player;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Talk implements Command{

    private static final Pattern PAYMENT_PATTERN = Pattern.compile("zaplatit\\s*([\\d\\s]+)", Pattern.CASE_INSENSITIVE);
    private static final String BESTIE_NAME = "bestie";
    private static final String QUEST_NODE_ID = "job";
    private static final String PAY_NODE_ID = "pay";
    private static final int QUEST_CYBERPSYCHOSIS_INCREASE = 30;
    private static final String VIKTOR_PISTOL_NAME = "Pistole od Viktora";

    private CommandManager commandManager;
    private DialogDAO dialogDAO;
    private ItemDAO itemDAO;

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
                        if(BESTIE_NAME.equals(npcName) && PAY_NODE_ID.equals(selectedOption.getNextDialogNodeId())){
                            grantViktorPistol(player);
                        }
                        interaction.setCurrentNodeId(selectedOption.getNextDialogNodeId());
                    } else {
                        System.out.println("Nemas dostatek eddies. Potrebujes " + paymentAmount + ". Vyber jinou moznost.");
                    }
                    continue;
                }

                if(BESTIE_NAME.equals(npcName) && QUEST_NODE_ID.equals(selectedOption.getNextDialogNodeId())){
                    try{
                        commandManager.getPlayer().increaseCyberpsychosis(QUEST_CYBERPSYCHOSIS_INCREASE);
                        System.out.println("Quest byl skipnut. Cyberpsychosis +30.");
                    } catch (InterruptedException e){
                        Thread.currentThread().interrupt();
                    }
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
        this.itemDAO = new ItemDAO();
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
            String numericAmount = matcher.group(1).replaceAll("\\D", "");
            if(numericAmount.isEmpty()){
                return 0;
            }
            return Integer.parseInt(numericAmount);
        } catch (NumberFormatException e){
            return 0;
        }
    }

    private void grantViktorPistol(Player player){
        if(player == null){
            return;
        }

        for(Item inventoryItem : player.getInventory()){
            if(inventoryItem.getName() != null
                    && inventoryItem.getName().equalsIgnoreCase(VIKTOR_PISTOL_NAME)){
                System.out.println("Pistoli od Viktora uz mas.");
                return;
            }
        }

        Item viktorPistol = itemDAO.getItemByName(VIKTOR_PISTOL_NAME);
        if(viktorPistol == null){
            System.out.println("Nepodarilo se najit pistoli od Viktora.");
            return;
        }

        if(player.addItem(viktorPistol)){
            System.out.println("Od Viktora jsi dostal jednoduchou pistoli.");
        } else {
            System.out.println("Inventar je plny. Pistoli od Viktora jsi nemohl prijmout.");
        }
    }
}
