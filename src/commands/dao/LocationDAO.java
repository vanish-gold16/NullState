package commands.dao;

import models.Item;
import models.Location;
import models.NPC;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationDAO {

    public LocationDAO() {
           loadLocationsFromFile("locations.json");
    }

    /**
     * Load locations from a file
     * @param filePath the path to the file
     */
    private void loadLocationsFromFile(String filePath){
        try{
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Gson gson = new Gson();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Get location by name
     * @param name the name of the location
     * @return
     */
    public Location getLocationByName(String name){
        return locations.get(name)
    }

    private static class LocationWrapper{
        List<LocationData> locations;
    }

}
