package main;

import commands.Command;
import commands.Enter;
import commands.Exit;
import commands.Talk;
import dao.DialogDAO;
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

    private Player player;
    private Location currentLocation;

    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, Command> commands = new HashMap<>();

    public CommandManager() {
        List<Item> inventory = new ArrayList<>();
        dialogDAO = new DialogDAO();
        npcDAO = new NPCDAO();
        player = new Player(inventory);
        currentLocation = new Location("Vstupni hala", "Jsi ve vstupni hale hradu.", false);
        inicialization();
    }

    /**
     * initializing commands
     */
    public void inicialization() {
        // commands.put("pomoc", )
        commands.put("exit", new Exit());
        // commands.put("status", )
        //commands.put("jdi", )
        commands.put("vstup", new Enter());
        commands.put("talk", new Talk(this, dialogDAO));
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
}
