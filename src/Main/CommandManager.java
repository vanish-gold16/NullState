package Main;

import Commands.Command;
import Commands.Exit;

import java.util.HashMap;

public class CommandManager {

    private HashMap<String, Command> commands = new HashMap<>();


    public void inicialization(){
        // commands.put("pomoc", )
        commands.put("exit", new Exit());
        // commands.put("status", )
        //commands.put("jdi", )
        commands.put("vstup", )
    }

}
