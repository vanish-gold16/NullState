package commands;

import main.CommandManager;
import models.Item;
import models.NPC;

import java.util.List;
import java.util.Random;

public class Hack implements Command{

    private static final Random RANDOM = new Random();
    private CommandManager commandManager;

    @Override
    public String execute() {
        if(!hasCyberdeck()){
            return "Nemáš kyberdeck.";
        }

        String args = commandManager.getScanner().nextLine().trim();
        if(args.isEmpty()){
            return "Zadej cíl. Použití: hackni <cil>";
        }

        List<NPC> npcs = commandManager.getCurrentLocation().getNpcs();
        NPC enemy = findEnemyByName(npcs, args);
        if(enemy != null){
            npcs.remove(enemy);
            try{
                Thread.sleep(1500);
                int extra = RANDOM.nextInt(2, 8);
                commandManager.getPlayer().increaseCyberpsychosis(5 + extra);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            return "Hack uspěšný. Zlikvidovani: " + enemy.getName();
        }

        Item item = findItemByName(commandManager.getCurrentLocation().getLocationItems(), args);
        if(item != null){
            try{
                int impact = RANDOM.nextInt(1, 4);
                commandManager.getPlayer().increaseCyberpsychosis(impact);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
            return "Hack uspěšný: " + item.getName();
        }

        return "Tento cíl tu není.";
    }

    @Override
    public boolean exit() {
        return false;
    }

    private boolean hasCyberdeck(){
        for(Item item : commandManager.getPlayer().getInventory()){
            if(item.getName().toLowerCase().contains("kyberdeck")){
                return true;
            }
        }
        return false;
    }

    private NPC findEnemyByName(List<NPC> npcs, String name){
        if(npcs == null) return null;
        for(NPC npc : npcs){
            if(npc.getName() != null && npc.getName().equalsIgnoreCase(name)){
                if(isEnemy(npc)){
                    return npc;
                }
            }
        }
        return null;
    }

    private boolean isEnemy(NPC npc){
        String affiliation = npc.getAffiliation();
        String npcName = npc.getName() == null ? "" : npc.getName().toLowerCase();
        if("Arasaka".equalsIgnoreCase(affiliation) || "Bergest".equalsIgnoreCase(affiliation)){
            return true;
        }
        return npcName.contains("strážce") || npcName.contains("dron") || npcName.contains("enemy");
    }

    private Item findItemByName(List<Item> items, String name){
        if(items == null) return null;
        for(Item item : items){
            if(item.getName().equalsIgnoreCase(name)){
                return item;
            }
        }
        return null;
    }

    public Hack(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
