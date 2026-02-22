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
    private static final String VIKTOR_NAME = "viktor";
    private static final String QUEST_NODE_ID = "job";
    private static final int QUEST_CYBERPSYCHOSIS_INCREASE = 30;
    private static final String VIKTOR_BUY_CYBERDECK_NODE_ID = "buy_cyberdeck";
    private static final String VIKTOR_BUY_INHIBITOR_NODE_ID = "buy_inhibitor";
    private static final String VIKTOR_GIFT_PISTOL_NODE_ID = "gift_pistol";
    private static final String CHEAP_CYBERDECK_NAME = "Levný Kyberdeck";
    private static final String INHIBITOR_NAME = "Inhibitor";
    private static final String SIMPLE_PISTOL_NAME = "Pistole Militech";

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
                        handleViktorPurchase(npcName, selectedOption.getNextDialogNodeId(), player);
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

                if(VIKTOR_NAME.equals(npcName) && VIKTOR_GIFT_PISTOL_NODE_ID.equals(selectedOption.getNextDialogNodeId())){
                    grantOneTimeItem(commandManager.getPlayer(), SIMPLE_PISTOL_NAME, "Od Viktora jsi dostal jednoduchou pistoli.");
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

    private void handleViktorPurchase(String npcName, String nextNodeId, Player player){
        if(!VIKTOR_NAME.equals(npcName) || nextNodeId == null){
            return;
        }

        if(VIKTOR_BUY_CYBERDECK_NODE_ID.equals(nextNodeId)){
            grantItem(player, CHEAP_CYBERDECK_NAME, "Koupil jsi Levny Kyberdeck.");
            return;
        }

        if(VIKTOR_BUY_INHIBITOR_NODE_ID.equals(nextNodeId)){
            grantItem(player, INHIBITOR_NAME, "Koupil jsi Inhibitor.");
        }
    }

    private void grantOneTimeItem(Player player, String itemName, String successMessage){
        if(player == null){
            return;
        }

        for(Item inventoryItem : player.getInventory()){
            if(inventoryItem.getName() != null
                    && inventoryItem.getName().equalsIgnoreCase(itemName)){
                System.out.println("Tento predmet uz mas.");
                return;
            }
        }

        grantItem(player, itemName, successMessage);
    }

    private void grantItem(Player player, String itemName, String successMessage){
        if(player == null || itemName == null || itemName.isBlank()){
            return;
        }

        Item item = itemDAO.getItemByName(itemName);
        if(item == null){
            System.out.println("Nepodarilo se najit predmet: " + itemName + ".");
            return;
        }

        if(player.addItem(item)){
            System.out.println(successMessage);
        } else {
            System.out.println("Inventar je plny. Predmet se nepodarilo pridat.");
        }
    }
}
