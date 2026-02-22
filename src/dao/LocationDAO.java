package dao;

import models.Location;
import models.Item;
import models.NPC;
import com.google.gson.Gson;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationDAO {

    private Map<String, Location> locations = new HashMap<>();
    private ItemDAO itemDAO;
    private NPCDAO npcDAO;

    public LocationDAO() {
        itemDAO = new ItemDAO();
        npcDAO = new NPCDAO();
        loadLocationsFromFile("jsons/locations.json");
    }

    /**
     * Loads locations, their items, NPCs and exits from JSON file,
     * and resolves references to in-memory objects.
     *
     * @param filePath location JSON path
     */
    private void loadLocationsFromFile(String filePath){
        try{
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Gson gson = new Gson();
            LocationWrapper wrapper = gson.fromJson(json, LocationWrapper.class);

            if(wrapper == null || wrapper.locations == null){
                return;
            }

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

            for(LocationData data : wrapper.locations){
                Location loc = locations.get(data.name);
                if(loc == null) continue;

                List<Item> items = new ArrayList<>();
                if(data.items != null){
                    for(String itemName : data.items){
                        Item item = itemDAO.getItemByName(itemName);
                        if(item != null){
                            items.add(item);
                        }
                    }
                }
                loc.setLocationItems(items);

                List<NPC> npcs = new ArrayList<>();
                if(data.npcs != null){
                    for(String npcName : data.npcs){
                        NPC npc = npcDAO.getNPCByName(npcName);
                        if(npc != null){
                            if(npc.getLocation() == null){
                                npc.setLocation(loc);
                            }
                            npcs.add(npc);
                        }
                    }
                }
                loc.setNpcs(npcs);

                Map<String, String> exits = new HashMap<>();
                if(data.exits != null){
                    for(Map.Entry<String, String> entry : data.exits.entrySet()){
                        if(locations.containsKey(entry.getValue())){
                            exits.put(entry.getKey(), entry.getValue());
                        }
                    }
                }
                loc.setExits(exits);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns location by exact name key.
     *
     * @param name location name
     * @return location instance or null when not found
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
        Map<String, String> exits;
    }

}
