package commands;

import main.CommandManager;

public class Enter implements Command {

    private CommandManager commandManager;

    @Override
    public String execute() {
        String args = commandManager.getScanner().nextLine();
        return commandManager.movePlayer(args, "Tam nemuzes vstoupit.");
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Enter(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
