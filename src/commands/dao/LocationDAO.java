package commands.dao;

import models.Location;

import java.util.HashMap;
import java.util.Map;

public class LocationDAO {

    private Map<String, Location> locations;

    public LocationDAO() {
        this.locations = new HashMap<>();
    }

    private void initializations(){
        String name = "Postranní úlička";
        String description =
                "Nacháziš se úzke uličce.";
    }
}
