package commands;

import models.Location;
import models.Player;

public interface Command {

    String execute();

    boolean exit();

}
