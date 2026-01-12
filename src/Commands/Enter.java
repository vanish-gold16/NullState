package Commands;

import Models.Location;
import Models.Player;

public class Enter implements Command{

    private Location location;
    private Location newLocation;

    @Override
    public String execute() {
        location.setName(newLocation.getName());
        location.setDescription(newLocation.getDescription());
        location.setLocationItems(newLocation.getLocationItems());
        location.setNpcs(newLocation.getNpcs());
        location.setExits(newLocation.getExits());
        return "You have entered: " + location.getName();
    }

    public Enter(Location location, Location newLocation) {
        this.location = location;
        this.newLocation = newLocation;
    }

    @Override
    public boolean exit() {
        return false;
    }
}
