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

    private Map<String, Location> locations = new HashMap<>();

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
            LocationWrapper wrapper = gson.fromJson(json, LocationWrapper.class);

            for(LocationData data : wrapper.locations){
                Location loc = new Location(
                        data.name,
                        data.description,
                        new ArrayList<>(),
                        new ArrayList<>(),
                        new HashMap<>()
                );
                locations.put(data.name, loc);
            }
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
        return locations.get(name);
    }

    private static class LocationWrapper{
        List<LocationData> locations;
    }

    private static class LocationData{
        String name;
        String description;
        List<String> items;
        List<String> npcs;
        Map<String, Location> exits;
    }

}
