package commands;

import main.CommandManager;
import models.Item;
import models.NPC;

import java.util.List;

public class Attack implements Command {

    private CommandManager commandManager;

    @Override
    public String execute() {
        // Consume any optional target text after "utok"
        // so leftover tokens are not parsed as new commands.
        commandManager.getScanner().nextLine();

        if (!hasPistol()) {
            return "Nemas pistoli.";
        }

        List<NPC> npcs = commandManager.getCurrentLocation().getNpcs();
        NPC enemy = findEnemy(npcs);
        if (enemy == null) {
            return "Na lokaci nejsou zadni nepratele.";
        }

        npcs.remove(enemy);
        try {
            commandManager.getPlayer().increaseCyberpsychosis(15);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return "Zlikvidoval jsi: " + enemy.getName();
    }

    @Override
    public boolean exit() {
        return false;
    }

    private boolean hasPistol() {
        for (Item item : commandManager.getPlayer().getInventory()) {
            if (item.getName().toLowerCase().contains("pistole")) {
                return true;
            }
        }
        return false;
    }

    private NPC findEnemy(List<NPC> npcs) {
        if (npcs == null) {
            return null;
        }
        for (NPC npc : npcs) {
            String affiliation = npc.getAffiliation();
            String name = npc.getName() == null ? "" : npc.getName().toLowerCase();
            if ("Arasaka".equalsIgnoreCase(affiliation) || "Bergest".equalsIgnoreCase(affiliation)) {
                return npc;
            }
            if (name.contains("strazce") || name.contains("dron") || name.contains("enemy")) {
                return npc;
            }
        }
        return null;
    }

    public Attack(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
