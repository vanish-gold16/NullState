package main;

import commands.*;
import dao.DialogDAO;
import dao.LocationDAO;
import dao.NPCDAO;
import models.Item;
import models.Location;
import models.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class CommandManager {

    private static DialogDAO dialogDAO;
    private NPCDAO npcDAO;
    private LocationDAO locationDAO;

    private Player player;
    private Location currentLocation;

    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, Command> commands = new HashMap<>();


    public CommandManager() {
        List<Item> inventory = new ArrayList<>();
        dialogDAO = new DialogDAO();
        npcDAO = new NPCDAO();
        locationDAO = new LocationDAO();
        player = new Player(inventory);
        currentLocation = locationDAO.getLocationByName("Postranní úlička");
        inicialization();
    }

    /**
     * initializing commands
     */
    public void inicialization() {
        // commands.put("pomoc", )
        commands.put("exit", new Exit());
        commands.put("status", new Status(this));
        commands.put("jdi", new Go(this));
        commands.put("vstup", new Enter());
        commands.put("mluv", new Talk(this, dialogDAO));
        commands.put("prozkoumej", new Examine());
        commands.put("vezmi", new Take());
        commands.put("poloz", new Drop());
        commands.put("pouzij", new Use());
        commands.put("utok", new Attack());
        commands.put("hackni", new Hack());
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

    public void setCurrentLocation(Location currentLocation) {
        this.currentLocation = currentLocation;
    }
}
