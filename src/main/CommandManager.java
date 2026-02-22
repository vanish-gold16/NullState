package main;

import commands.*;
import dao.DialogDAO;
import dao.ItemDAO;
import dao.LocationDAO;
import dao.NPCDAO;
import models.Item;
import models.Location;
import models.NPC;
import models.Player;
import models.SaveData;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CommandManager {

    private static final String END_LOCATION_NAME = "Koncovka";
    private static final int MOVE_CYBERPSYCHOSIS_INCREASE = 10;
    private static final String MARKET_LOCATION = "trh v male cine";
    private static final String DIRECTION_NORTH = "sever";

    private static DialogDAO dialogDAO;

    private NPCDAO npcDAO;
    private LocationDAO locationDAO;
    private SaveManager saveManager;

    private Player player;
    private Location currentLocation;

    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, Command> commands = new HashMap<>();

    private boolean isExit;
    private boolean bestieDialogueCompleted;

    public CommandManager() {
        saveManager = new SaveManager();
        isExit = false;
        bestieDialogueCompleted = false;

        List<Item> inventory = new ArrayList<>();
        dialogDAO = new DialogDAO();
        npcDAO = new NPCDAO();
        locationDAO = new LocationDAO();
        player = new Player(inventory);
        currentLocation = locationDAO.getLocationByName("Postranní ulička");

        initialization();
    }

    /**
     * saves current game state to disk.
     */
    public void saveGame() {
        saveManager.saveGame(player, currentLocation.getName(), bestieDialogueCompleted);
    }

    /**
     * Loads game state from the save file
     */
    public void loadGame() {
        SaveData saveData = saveManager.loadGame();
        if (saveData == null) {
            return;
        }

        Location savedLocation = locationDAO.getLocationByName(saveData.getCurrentLocationName());
        if (savedLocation != null) {
            currentLocation = savedLocation;
        }

        player.setEddie(saveData.getMoney());
        player.setCyberpsychosisLevel(saveData.getCyberpsychosis());
        bestieDialogueCompleted = saveData.isBestieDialogueCompleted();

        ItemDAO itemDAO = new ItemDAO();
        List<Item> inventory = new ArrayList<>();
        if (saveData.getInventoryItemNames() != null) {
            for (String itemName : saveData.getInventoryItemNames()) {
                Item item = itemDAO.getItemByName(itemName);
                if (item != null) {
                    inventory.add(item);
                }
            }
        }

        player.getInventory().clear();
        player.getInventory().addAll(inventory);
    }

    /**
     * Checks whether a save file exists and can be loaded.
     * @return true if save file exists
     */
    public boolean hasSave() {
        return saveManager.saveExists();
    }

    /**
     * Runs one full command-processing cycle.
     */
    public void start() {
        initialization();
        execute();
    }

    /**
     * Initializes commands.
     */
    public void initialization() {
        commands.put("pomoc", new Help(this));
        commands.put("exit", new Exit());
        commands.put("status", new Status(this));
        commands.put("jdi", new Go(this));
        commands.put("vstup", new Enter(this));
        commands.put("mluv", new Talk(this, dialogDAO));
        commands.put("prozkoumej", new Examine(this));
        commands.put("vezmi", new Take(this));
        commands.put("poloz", new Drop(this));
        commands.put("pouzij", new Use(this));
        commands.put("inventar", new Inventory(this));
        commands.put("utok", new Attack(this));
        commands.put("hackni", new Hack(this));
        commands.put("help", new Help(this));
    }

    /**
     * Reads and executes one user command and then checks game-end condition.
     */
    public void execute() {
        System.out.print(">> ");
        String command = scanner.next();
        command = command.trim().toLowerCase();

        if (commands.containsKey(command)) {
            System.out.println(commands.get(command).execute());
            isExit = commands.get(command).exit();
            if (!isExit) {
                checkGameEnd();
            }
        } else {
            System.out.println("Spatny prikaz, zadej znovu.");
        }
    }

    /**
     * Validates and does movement from the current location.
     * @param rawDirection user-entered direction text
     * @param invalidDirectionMessage message to display when direction is not available
     * @return result text describing movement outcome
     */
    public String movePlayer(String rawDirection, String invalidDirectionMessage) {
        String directionInput = rawDirection == null ? "" : rawDirection.trim().toLowerCase();
        if (directionInput.isEmpty()) {
            return showAvailableExits();
        }

        Location location = getCurrentLocation();
        if (location == null) {
            return "Aktualni lokace neni dostupna.";
        }

        Map<String, String> exits = location.getExits();
        if (exits == null || exits.isEmpty()) {
            return "V teto lokaci nejsou zadne vychody.";
        }

        String movementLockMessage = getMovementLockMessage(location);
        if (movementLockMessage != null) {
            return movementLockMessage;
        }

        String normalizedDirection = normalizeDirection(directionInput);
        if (isMarketNorthBlocked(location, normalizedDirection)) {
            return "Cesta na sever z trhu je zatim zamcena. Nejdriv si promluv s Bestii v Afterlife.";
        }

        String resolvedDirection = resolveExitDirection(exits, directionInput);
        if (resolvedDirection == null) {
            return invalidDirectionMessage + "\n" + showAvailableExits();
        }

        String nextLocationName = exits.get(resolvedDirection);
        Location nextLocation = locationDAO.getLocationByName(nextLocationName);
        if (nextLocation == null) {
            return "Tato lokace neexistuje.";
        }

        setCurrentLocation(nextLocation);
        return "Presun uspesny.";
    }

    /**
     * Returns a blocking movement reason when location still contains hostile NPCs.
     * @param location current location
     * @return lock message or null when movement is allowed
     */
    private String getMovementLockMessage(Location location) {
        if (location == null || location.getNpcs() == null) {
            return null;
        }

        for (NPC npc : location.getNpcs()) {
            if (isHostile(npc)) {
                return "Nejdriv musis zlikvidovat nepratele v lokaci.";
            }
        }
        return null;
    }

    /**
     * Determines whether NPC should be treated as hostile for movement lock.
     * @param npc NPC to evaluate
     * @return true when NPC is enemy-like by affiliation or name marker
     */
    private boolean isHostile(NPC npc) {
        if (npc == null) {
            return false;
        }

        String affiliation = npc.getAffiliation() == null ? "" : npc.getAffiliation();
        if ("Arasaka".equalsIgnoreCase(affiliation) || "Bergest".equalsIgnoreCase(affiliation)) {
            return true;
        }

        String normalizedName = normalizeText(npc.getName());
        return normalizedName.contains("strazce")
                || normalizedName.contains("dron")
                || normalizedName.contains("robot")
                || normalizedName.contains("enemy");
    }

    /**
     * Applies story gate that blocks north exit from market
     * until Bestie dialogue is completed.
     * @param location current location
     * @param normalizedDirection normalized movement direction
     * @return true when gate is still locked
     */
    private boolean isMarketNorthBlocked(Location location, String normalizedDirection) {
        if (location == null) {
            return false;
        }
        String normalizedLocation = normalizeText(location.getName());
        return MARKET_LOCATION.equals(normalizedLocation)
                && DIRECTION_NORTH.equals(normalizedDirection)
                && !bestieDialogueCompleted;
    }

    private String showAvailableExits() {
        Location location = getCurrentLocation();
        if (location == null || location.getExits() == null || location.getExits().isEmpty()) {
            return "V teto lokaci nejsou zadne vychody.";
        }
        return "Dostupne vychody:\n" + String.join(",", location.getExits().keySet());
    }

    private String resolveExitDirection(Map<String, String> exits, String inputDirection) {
        if (exits == null || exits.isEmpty()) {
            return null;
        }

        String normalizedInput = normalizeDirection(inputDirection);
        for (String key : exits.keySet()) {
            if (normalizeDirection(key).equals(normalizedInput)) {
                return key;
            }
        }
        return null;
    }

    private String normalizeDirection(String input) {
        String normalized = normalizeText(input);
        return switch (normalized) {
            case "s", "sever" -> "sever";
            case "j", "jih" -> "jih";
            case "v", "vychod" -> "vychod";
            case "z", "zapad" -> "zapad";
            default -> normalized;
        };
    }

    private String normalizeText(String text) {
        if (text == null) {
            return "";
        }
        String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toLowerCase();
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }

    public Player getPlayer() {
        return player;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }

    public NPCDAO getNpcDAO() {
        return npcDAO;
    }

    public static DialogDAO getDialogDAO() {
        return dialogDAO;
    }

    public LocationDAO getLocationDAO() {
        return locationDAO;
    }

    public boolean isBestieDialogueCompleted() {
        return bestieDialogueCompleted;
    }

    public void setBestieDialogueCompleted(boolean bestieDialogueCompleted) {
        this.bestieDialogueCompleted = bestieDialogueCompleted;
    }

    /**
     * Updates current location and applies movement cyberpsychosis increase
     * only if the location actually changed.
     *
     * @param currentLocation destination location
     */
    public void setCurrentLocation(Location currentLocation) {
        if (currentLocation == null) {
            return;
        }

        boolean locationChanged = this.currentLocation == null
                || !this.currentLocation.getName().equals(currentLocation.getName());

        this.currentLocation = currentLocation;

        if (locationChanged && player != null) {
            try {
                player.increaseCyberpsychosis(MOVE_CYBERPSYCHOSIS_INCREASE);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean isExit() {
        return isExit;
    }

    private void checkGameEnd() {
        if (currentLocation != null && END_LOCATION_NAME.equals(currentLocation.getName())) {
            System.out.println("Dosahl jsi konce hry.");
            isExit = true;
        }
    }
}
