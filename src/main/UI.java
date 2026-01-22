package main;

import models.Location;

public class UI {

    private CommandManager commandManager;
    private boolean running;

    public UI(){
        commandManager = new CommandManager();
        running = false;
    }

    /**
     * Starts the application
     */
    public void start(){
        running = true;
        Location location = commandManager.getCurrentLocation();
        if(location != null){
            System.out.println(location.getName());
            System.out.println(location.getDescription());
        }
        commandManager.start();
        running = false;
    }

    public boolean isRunning() {
        return running;
    }
}
