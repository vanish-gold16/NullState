package Commands;

import Locations.Location;
import Models.Player;

public class Enter implements Command{

    private Location location;
    private Player player;

    @Override
    public String execute() {
        return location.setNa;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
