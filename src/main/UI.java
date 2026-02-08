package main;

import models.Location;

public class UI {

    private TextPrinter printer;
    private CommandManager commandManager;
    private boolean running;

    public UI(){
        commandManager = new CommandManager();
        printer = new TextPrinter();
        running = false;
    }

    /**
     * Starts the application
     */
    public void start(){
        running = true;
        java.util.Scanner menuScanner = new java.util.Scanner(System.in);

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

    public void test(){
        printer.type("haha pidrilla a a sasfasagasaf ", 60);
    }

    private void runGameLoop(){
        test();
        while(running && !commandManager.isExit()){
            Location location = commandManager.getCurrentLocation();
            if(location != null){
                System.out.println(location.getName());
                System.out.println(location.getDescription());
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
