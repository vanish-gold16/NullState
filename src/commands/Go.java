package commands;

import main.CommandManager;
import models.Location;

import java.util.Map;

public class Go implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim().toLowerCase();

        if(args.isEmpty()) return showAvailibleExits();

        Location currentLocation = commandManager.getCurrentLocation();
        Map<String, Location> exits = currentLocation.getExits();

        if(exits == null || exits.isEmpty()) return "V této lokaci nejsou žádné východy.";

        String direction = normalizeDirection(args);

        if(!exits.containsKey(direction)){
            return "Tam nemůžeš.\n" + showAvailibleExits();
        }

        String nextLocationName = String.valueOf(exits.get(direction));
        Location nextLocation = commandManager.getLocationDAO().getLocationByName(nextLocationName);

        if(nextLocation == null) return "Tato lokace neexistuje.";

        commandManager.setCurrentLocation(nextLocation);

        return commandManager.getCurrentLocation().getName() + "\n" + commandManager.getCurrentLocation().getDescription();
    }

    @Override
    public boolean exit() {
        return false;
    }

    private String normalizeDirection(String input){
        return switch (input){
            case "s", "sever" -> "sever";
            case "j", "jih" -> "jih";
            case "v", "vychod" -> "vychod";
            case "z", "zapad" -> "zapad";
            default -> input;
        };
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
