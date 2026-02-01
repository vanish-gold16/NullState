package commands;

import main.CommandManager;

public class Status implements Command{

    private CommandManager commandManager;

    @Override
    public String execute() {
        StringBuilder playerStatus = new StringBuilder();
        playerStatus.append("---- Status Hrace ----\n");
        playerStatus.append(commandManager.getPlayer().formatInventory()).append("\n");
        playerStatus.append(commandManager.getPlayer().getEddie()).append(" eddies\n");
        playerStatus.append(commandManager.getPlayer().getCyberpsychosisLevel()).append(" cyberpsychosis level\n");

        return playerStatus.toString();
    }

    @Override
    public boolean exit() {
        return false;
    }

    public Status(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
