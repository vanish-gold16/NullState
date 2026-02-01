package commands;

import main.CommandManager;
import models.Location;

import java.util.Map;

public class Enter implements Command{

    private CommandManager commandManager;

    /**
     * Enter location via available exits (alias to movement).
     * @return name of location you've entered
     */
    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim().toLowerCase();

        if(args.isEmpty()){
            return showAvailibleExits();
        }

        Location currentLocation = commandManager.getCurrentLocation();
        Map<String, String> exits = currentLocation.getExits();

        if(exits == null || exits.isEmpty()){
            return "V teto lokaci nejsou zadne vychody.";
        }

        String direction = normalizeDirection(args);
        if(!exits.containsKey(direction)){
            return "Tam nemuzes vstoupit.\n" + showAvailibleExits();
        }

        String nextLocationName = exits.get(direction);
        Location nextLocation = commandManager.getLocationDAO().getLocationByName(nextLocationName);
        if(nextLocation == null){
            return "Tato lokace neexistuje.";
        }

        commandManager.setCurrentLocation(nextLocation);
        return commandManager.getCurrentLocation().getName() + "\n" + commandManager.getCurrentLocation().getDescription();
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
        Map<String, String> exits = currentLocation.getExits();

        if(exits == null || exits.isEmpty()){
            return "V teto lokaci nejsou zadne vychody.";
        }

        StringBuilder stringBuilder = new StringBuilder("Dostupne vychody:\n");
        stringBuilder.append(String.join(",", exits.keySet()));
        return stringBuilder.toString();
    }

    public Enter(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
