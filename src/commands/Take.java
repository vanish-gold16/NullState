package commands;

import main.CommandManager;
import models.Item;

import java.text.Normalizer;
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
        return buildPickupMessage(found);
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Take(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    /**
     * Builds pickup result message and appends lore text for special collectible notes.
     * @param item picked up item
     * @return final message displayed to player
     */
    private String buildPickupMessage(Item item){
        String baseMessage = "Vzal jsi: " + item.getName();
        String loreMessage = getLorePickupText(item.getName());
        if(loreMessage == null){
            return baseMessage;
        }
        return baseMessage + "\n\n" + loreMessage;
    }

    /**
     * Returns additional story text
     * @param itemName picked up item name
     * @return lore text for supported items or null for regular items
     */
    private String getLorePickupText(String itemName){
        String normalized = normalize(itemName);
        if("zapisky netrunnera".equals(normalized)){
            return "Síť už dávno není jen nástroj. Je to organismus. \n" +
                    "Roste, učí se a pamatuje si víc, než by měla. \n" +
                    "Lidé si mysleli, že po Datacrash je pod kontrolou. Není. Nikdy nebyla.\n" +
                    "Korporace staví firewally jako zdi starých pevností, ale zapomínají, \n" +
                    "že každá pevnost padne zevnitř. \n" +
                    "Největší hrozbou nejsou AI duchové ani černé ICE. \n" +
                    "Jsou to ambice lidí, kteří věří, že mohou vlastnit samotnou informaci.";
        }
        if("zapisky neznameho bojovnika 2. korporativni valky".equals(normalized)){
            return "Den 43.\n" +
                    "Už si nepamatuji, za koho vlastně bojuji. Loga na uniformách se mění rychleji než rozkazy. \n" +
                    "Včera jsme chránili konvoj s léky. Dnes jsme ten samý konvoj přepadli, protože „změna smlouvy“.\n" +
                    "Říkají tomu válka. Ale války mívají ideály.";
        }
        return null;
    }

    private String normalize(String value){
        if(value == null){
            return "";
        }
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toLowerCase().trim();
    }
}
