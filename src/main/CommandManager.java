package main;

import commands.Command;
import commands.Enter;
import commands.Exit;
import commands.Talk;
import dao.DialogDAO;
import dao.NPCDAO;
import models.Location;

import java.util.HashMap;
import java.util.Scanner;

public class CommandManager {

    private static DialogDAO dialogDAO;
    private NPCDAO npcDAO;

    private Location currentLocation;

    private Scanner scanner = new Scanner(System.in);
    private HashMap<String, Command> commands = new HashMap<>();

    /**
     * initializing commands
     */
    public void inicialization() {
        // commands.put("pomoc", )
        commands.put("exit", new Exit());
        // commands.put("status", )
        //commands.put("jdi", )
        commands.put("vstup", new Enter());
        commands.put("talk", new Talk(dialogDAO));
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
