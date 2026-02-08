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
    private void runGameLoop(){

        printer.type("Říká se, že okvětní lístky sakury padají rychlostí pěti centimetrů za sekundu. ", 2500);
        printer.type("Nemohu si to ověřit. ", 2000);
        printer.type("V tomhle městě žádné sakury nerostou. \n", 3000);
        printer.type("""           
                |   | |   | |     |    
                | | | |   | |     |    
                |   | | _ | | _ _ | _ _\n""", 5000);
        skip();
        printer.type("Pronásleduje tě krutá bolest hlavy, někde v oblasti přístavu. ", 1000);
        printer.type("Všechno kolem je syrové a nepřátelské. ", 2000);
        printer.type("Asfalt je stále mokrý po nedávném, zde vzácném dešti. ", 3000);
        printer.type("Úzká ulička, sevřená podivně starými budovami. ", 1500);
        printer.type("Vzpomínky na včerejšek se pomalu vracejí. ", 1500);
        printer.type("Útržky událostí. ", 1000);
        printer.type("A pak i důvod, proč ti v chrámu tepá bolest. ", 2000);
        printer.type("Arasaka.", 2000);

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
    public void skip(){
        System.out.println("\n\n\n\n\n\n\n");
    }

    public boolean isRunning() {
        return running;
    }
}
