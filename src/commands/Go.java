package commands;

import main.CommandManager;

public class Go implements Command {

    private CommandManager commandManager;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine();
        return commandManager.movePlayer(args, "Tam nemuzes.");
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Go(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
