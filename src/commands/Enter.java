package commands;

import main.CommandManager;
import models.Location;

import java.text.Normalizer;
import java.util.Map;

public class Enter implements Command {

    private CommandManager commandManager;

    /**
     * Enter location via available exits (alias to movement).
     * @return name of location you've entered
     */
    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim().toLowerCase();

        if (args.isEmpty()) {
            return showAvailibleExits();
        }

        Location currentLocation = commandManager.getCurrentLocation();
        Map<String, String> exits = currentLocation.getExits();

        if (exits == null || exits.isEmpty()) {
            return "V teto lokaci nejsou zadne vychody.";
        }

        String direction = resolveExitDirection(exits, args);
        if (direction == null) {
            return "Tam nemuzes vstoupit.\n" + showAvailibleExits();
        }

        String nextLocationName = exits.get(direction);
        Location nextLocation = commandManager.getLocationDAO().getLocationByName(nextLocationName);
        if (nextLocation == null) {
            return "Tato lokace neexistuje.";
        }

        commandManager.setCurrentLocation(nextLocation);
        return "Presun uspesny.";
    }

    private String normalizeDirection(String input) {
        String normalized = stripDiacritics(input == null ? "" : input.trim().toLowerCase());
        return switch (normalized) {
            case "s", "sever" -> "sever";
            case "j", "jih" -> "jih";
            case "v", "vychod" -> "vychod";
            case "z", "zapad" -> "zapad";
            default -> normalized;
        };
    }

    private String resolveExitDirection(Map<String, String> exits, String inputDirection) {
        if (exits == null || exits.isEmpty()) {
            return null;
        }

        String normalizedInput = normalizeDirection(inputDirection);
        for (String key : exits.keySet()) {
            String normalizedKey = normalizeDirection(key);
            if (normalizedKey.equals(normalizedInput)) {
                return key;
            }
        }
        return null;
    }

    private String stripDiacritics(String text) {
        if (text == null) {
            return "";
        }
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "");
    }

    private String showAvailibleExits() {
        Location currentLocation = commandManager.getCurrentLocation();
        Map<String, String> exits = currentLocation.getExits();

        if (exits == null || exits.isEmpty()) {
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
