package commands;

import main.CommandManager;
import models.Item;

import java.util.Iterator;
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
            return "Zadej název předmětu. Použití: poloz <predmet>";
        }

        List<Item> inventory = commandManager.getPlayer().getInventory();
        if(inventory.isEmpty()){
            return "Inventář je prázdný.";
        }

        Item found = null;
        Iterator<Item> iterator = inventory.iterator();
        while(iterator.hasNext()){
            Item item = iterator.next();
            if(item.getName().equalsIgnoreCase(args)){
                found = item;
                iterator.remove();
                break;
            }
        }

        if(found == null){
            return "Tento předmět nemáš.";
        }

        commandManager.getCurrentLocation().getLocationItems().add(found);
        return "Položil jsi: " + found.getName();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
