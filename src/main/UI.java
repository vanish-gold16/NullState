package main;

import dao.DialogDAO;
import models.Location;

import java.text.Normalizer;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class UI {

    private static final Set<String> CHECKPOINT_LOCATIONS = new HashSet<>(Set.of(
            "trh v male cine",
            "tunely metra",
            "dogtown",
            "opustena budova"
    ));

    private TextPrinter printer;
    private CommandManager commandManager;
    private DialogDAO dialogDAO;

    private boolean running;

    public UI() {
        commandManager = new CommandManager();
        dialogDAO = new DialogDAO();
        printer = new TextPrinter();
        running = false;
    }

    /**
     * Starts the application
     */
    public void start() {
        running = true;
        Scanner menuScanner = new Scanner(System.in);

        while (running) {
            System.out.println("=== NullState ===");
            System.out.println("[1] Nova hra");
            System.out.println("[2] Nahrat hru");
            System.out.println("[3] Konec");
            System.out.println("Ukladani probiha jen v dulezitych bodech pribehu.");
            System.out.print("> ");

            String choice = menuScanner.nextLine().trim();

            switch (choice) {
                case "1":
                    commandManager = new CommandManager();
                    commandManager.saveGame();
                    runGameLoop();
                    break;
                case "2":
                    commandManager = new CommandManager();
                    if (!commandManager.hasSave()) {
                        System.out.println("Zadny ulozeny postup nebyl nalezen.");
                        continue;
                    }
                    commandManager.loadGame();
                    runGameLoop();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Neplatna volba.");
            }
        }
    }

    private void runGameLoop() {
        String lastShownLocationName = null;
         Set<String> visitedStoryLocations = new HashSet<>();

        while (running && !commandManager.isExit()) {
            Location location = commandManager.getCurrentLocation();

            if (location != null && !location.getName().equals(lastShownLocationName)) {
                String normalizedLocationName = normalizeLocationName(location.getName());
                boolean isFirstStoryVisit = visitedStoryLocations.add(normalizedLocationName);

                if (isFirstStoryVisit) {
                    showLocationArt(location.getName());
                }

                System.out.println();
                System.out.println("Cyberpsychosis: " + commandManager.getPlayer().getCyberpsychosisLevel());
                System.out.println(location.getName());
                System.out.println(location.getDescription());
                System.out.println(location.getExits());
                System.out.println("NPC: " + location.getNpcs());
                System.out.println("Items: " + location.getLocationItems());
                System.out.println();

                if (isCheckpointLocation(location.getName())) {
                    commandManager.saveGame();
                }

                lastShownLocationName = location.getName();
            }

            commandManager.start();
        }
    }

    private void showLocationArt(String locationName) {
        String normalized = normalizeLocationName(locationName);
        switch (normalized) {
            case "postranni ulicka":
                printer.backstreet();
                break;
            case "trh v male cine":
                printer.market();
                System.out.println(printer.marketHint());
                break;
            case "afterlife":
                printer.bar();
                break;
            case "viktor's clinic":
                printer.clinic();
                break;
            case "vstup do metra":
                printer.metroEnter();
                break;
            case "tunely metra":
                printer.tunels();
                break;
            case "dogtown":
                printer.dogtown();
                break;
            case "opustena budova":
                printer.building();
                break;
            case "serverovna":
                printer.serverRoom();
                break;
            case "koncovka":
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + locationName);
        }
    }

    private boolean isCheckpointLocation(String locationName) {
        if (locationName == null) {
            return false;
        }
        return CHECKPOINT_LOCATIONS.contains(normalizeLocationName(locationName));
    }

    private String normalizeLocationName(String value) {
        if (value == null) {
            return "";
        }
        String normalized = Normalizer.normalize(value, Normalizer.Form.NFD);
        return normalized.replaceAll("\\p{M}", "").toLowerCase();
    }

    public boolean isRunning() {
        return running;
    }
}
