package commands;

import main.CommandManager;
import models.Item;
import models.Player;

import java.util.Iterator;
import java.util.List;

public class Use implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim();
        if(args.isEmpty()){
            return "Zadej název předmětu. Použití: pouzij <predmet>";
        }

        Player player = commandManager.getPlayer();
        List<Item> inventory = player.getInventory();
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

        if(!"consumable".equalsIgnoreCase(found.getType())){
            inventory.add(found);
            return "Tento předmět nelze použit.";
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

        return "Použil jsi: " + found.getName();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Use(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
