package commands;

import main.CommandManager;

public class Drop implements Command{
    private CommandManager commandManager;

    public Drop(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine().trim().toLowerCase();
        return "";
    }

    @Override
    public boolean exit() {
        return false;
    }
}
