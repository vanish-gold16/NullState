package commands;

import main.CommandManager;
import models.Item;

import java.util.Iterator;
import java.util.List;

public class Take implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim();
        if(args.isEmpty()){
            return "Zadej název předmetu. Použití: vezmi <predmet>";
        }

        List<Item> inventory = commandManager.getPlayer().getInventory();
        if(inventory.size() >= 2){
            return "Inventář je plny.";
        }

        List<Item> items = commandManager.getCurrentLocation().getLocationItems();
        if(items == null || items.isEmpty()){
            return "Na lokaci nejsou žádné předměty.";
        }

        Item found = null;
        Iterator<Item> iterator = items.iterator();
        while(iterator.hasNext()){
            Item item = iterator.next();
            if(item.getName().equalsIgnoreCase(args)){
                found = item;
                iterator.remove();
                break;
            }
        }

        if(found == null){
            return "Tento predmet tu neni.";
        }

        inventory.add(found);
        return "Vzal jsi: " + found.getName();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Take(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
