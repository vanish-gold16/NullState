package main;

import commands.Command;
import commands.Enter;
import commands.Exit;

import java.util.HashMap;

public class CommandManager {

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
    }

}
