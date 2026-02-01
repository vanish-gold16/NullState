package commands;

import main.CommandManager;
import models.Item;

import java.util.List;

public class Drop implements Command{
    private CommandManager commandManager;

    public Drop(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim();
        if(args.isEmpty()){
            return "Zadej nazev predmetu. Pouziti: poloz <predmet>";
        }

        List<Item> inventory = commandManager.getPlayer().getInventory();
        if(inventory.isEmpty()){
            return "Inventar je prazdny.";
        }

        Item found = commandManager.getPlayer().removeItemByName(args);
        if(found == null){
            return "Tento predmet nemas.";
        }

        commandManager.getCurrentLocation().getLocationItems().add(found);
        return "Polozil jsi: " + found.getName();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
