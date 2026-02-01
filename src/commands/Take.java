package commands;

import main.CommandManager;
import models.Item;

import java.util.List;

public class Take implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim();
        if(args.isEmpty()){
            return "Zadej nazev predmetu. Pouziti: vezmi <predmet>";
        }

        List<Item> items = commandManager.getCurrentLocation().getLocationItems();
        if(items == null || items.isEmpty()){
            return "Na lokaci nejsou zadne predmety.";
        }

        Item found = null;
        for(Item item : items){
            if(item.getName().equalsIgnoreCase(args)){
                found = item;
                break;
            }
        }

        if(found == null){
            return "Tento predmet tu neni.";
        }

        if(!commandManager.getPlayer().addItem(found)){
            return "Inventar je plny.";
        }

        items.remove(found);
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
