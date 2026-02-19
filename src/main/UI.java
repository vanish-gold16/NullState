package main;

import dao.DialogDAO;
import models.Location;

import java.util.Scanner;

public class UI {

    private TextPrinter printer;
    private CommandManager commandManager;
    private DialogDAO dialogDAO;

    private boolean running;

    public UI(){
        commandManager = new CommandManager();
        dialogDAO = new DialogDAO();
        printer = new TextPrinter();
        running = false;
    }

    /**
     * Starts the application
     */
    public void start(){

        running = true;
        Scanner menuScanner = new Scanner(System.in);

        while(running){
            System.out.println("=== NullState ===");
            System.out.println("[1] Nova hra");
            System.out.println("[2] Nahrat hru");
            System.out.println("[3] Konec");
            System.out.print("> ");

            String choice = menuScanner.nextLine().trim();

            switch (choice){
                case "1":
                    commandManager = new CommandManager();
                    runGameLoop();
                    break;
                case "2":
                    commandManager = new CommandManager();
                    if(!commandManager.hasSave()){
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
    private void runGameLoop(){

        while(running && !commandManager.isExit()){
            Location location = commandManager.getCurrentLocation();

            switch(location.getName()){
                case("Postranní ulička"):
                    printer.backstreet();
                    break;
                case("Trh v Malé Číně"):
                    printer.market();
                    break;
                case("Afterlife"):
                    printer.
                default:
                    throw new IllegalStateException("Unexpected value: " + location);
            }

            if(location != null){
                System.out.println();
                System.out.println(location.getName());
                System.out.println(location.getDescription());
                System.out.println(location.getExits());
                System.out.println("NPC: " + location.getNpcs());
                System.out.println(location.getLocationItems());
                System.out.println();
            }

            commandManager.start();

            if(!commandManager.isExit()){
                commandManager.saveGame();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }
}
