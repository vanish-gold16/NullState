package commands;

import main.CommandManager;
import models.Item;

import java.util.List;

public class Examine implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        List<Item> items = commandManager.getCurrentLocation().getLocationItems();
        if(items == null || items.isEmpty()){
            return "Na lokaci nejsou zadne predmety.";
        }

        StringBuilder sb = new StringBuilder("Predmety na lokaci:\n");
        for(Item item : items){
            sb.append("- ").append(item.getName()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Examine(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
