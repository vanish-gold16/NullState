package commands;

import main.CommandManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Help implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        List<String> names = new ArrayList<>(commandManager.getCommands().keySet());
        Collections.sort(names);
        return "Dostupné příkazy: " + String.join(", ", names);
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Help(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
