package commands;

import models.Location;

public class Enter implements Command{

    private Location location;
    private Location newLocation;

    /**
     * changing current location
     * @return name of location you've entered
     */
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

    public Enter() {
    }

    @Override
    public boolean exit() {
        return false;
    }
}
