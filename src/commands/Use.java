package commands;

import main.CommandManager;
import models.Item;
import models.Player;

import java.util.List;

public class Use implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim();
        if(args.isEmpty()){
            return "Zadej nazev predmetu. Pouziti: pouzij <predmet>";
        }

        Player player = commandManager.getPlayer();
        List<Item> inventory = player.getInventory();
        if(inventory.isEmpty()){
            return "Inventar je prazdny.";
        }

        Item found = player.removeItemByName(args);
        if(found == null){
            return "Tento predmet nemas.";
        }

        if(!"consumable".equalsIgnoreCase(found.getType())){
            player.addItem(found);
            return "Tento predmet nelze pouzit.";
        }

        int impact = found.getImpact();
        if(impact > 0){
            try{
                player.increaseCyberpsychosis(impact);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        } else if(impact < 0){
            int newLevel = player.getCyberpsychosisLevel() + impact;
            if(newLevel < 0){
                newLevel = 0;
            }
            player.setCyberpsychosisLevel(newLevel);
        }

        return "Pouzil jsi: " + found.getName();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Use(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
