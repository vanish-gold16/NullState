package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Location {

    private String name;
    private String description;
    private List<Item> locationItems = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private HashMap<String, Location> exits = new HashMap<>();

    public Location(String name, String description, List<Item> locationItems, List<NPC> npcs, HashMap<String, Location> exits) {
        this.name = name;
        this.description = description;
        this.locationItems = locationItems;
        this.npcs = npcs;
        this.exits = exits;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Item> getLocationItems() {
        return locationItems;
    }

    public void setLocationItems(List<Item> locationItems) {
        this.locationItems = locationItems;
    }

    public List<NPC> getNpcs() {
        return npcs;
    }

    public void setNpcs(List<NPC> npcs) {
        this.npcs = npcs;
    }

    public HashMap<String, Location> getExits() {
        return exits;
    }

    public void setExits(HashMap<String, Location> exits) {
        this.exits = exits;
    }
}
