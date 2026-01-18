package commands;

import main.CommandManager;
import models.Location;

import java.util.Map;

public class Go implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim().toLowerCase();

        if(args.isEmpty()) return

        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }

    private String showAvailibleExits() {
        Location currentLocation = commandManager.getCurrentLocation();
        Map<String, Location> exits = currentLocation.getExits();

        if(exits == null || exits.isEmpty()) return "V této lokaci nejsou žádné východy.";

        StringBuilder stringBuilder = new StringBuilder("Dostupné východy:\n");
        stringBuilder.append(String.join(",", exits.keySet()));
        return stringBuilder.toString();
    }

    public Go(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
