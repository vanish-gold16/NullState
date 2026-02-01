package commands;

import main.CommandManager;

public class Inventory implements Command{

    private CommandManager commandManager;

    public Inventory(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    @Override
    public String execute() {
        return commandManager.getPlayer().formatInventory();
    }

    @Override
    public boolean exit() {
        return false;
    }
}
