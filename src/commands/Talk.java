package commands;

import dao.DialogDAO;
import main.CommandManager;
import models.*;

import java.util.Map;

public class Talk implements Command{

    private CommandManager commandManager;
    private DialogDAO dialogDAO;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim();

        if(args.equals("talk")) return "Použití: mluv <jméno_NPC>";

        if(args.isEmpty()) {
            return "Zadej s kym chceš mluvit. Použití: mluv <jméno_NPC>";
        }

        String npcName = args.toLowerCase();
        NPC npc = commandManager.getCurrentLocation().getNpcs().stream()
                .filter(n -> n.getName().toLowerCase().equals(npcName))
                .findFirst().orElse(null);

        if(npc == null) {
            return "Tento NPC zde není.";
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
                    System.out.println("Neplatná volba.");
                    continue;
                }
                DialogueOption selectedOption = currentNode.getOptions().get(choice - 1);
                interaction.setCurrentNodeId(selectedOption.getNextDialogNodeId());
            } catch(NumberFormatException e){
                System.out.println("Zadej číslo odpovědi.");
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
}
