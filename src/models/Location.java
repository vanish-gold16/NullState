package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Location {

    private String name;
    private String description;
    private List<Item> locationItems = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private Map<String, String> exits;

    public Location(String name, String description, List<Item> locationItems, List<NPC> npcs, Map<String, String> exits) {
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

    public Map<String, String> getExits() {
        return exits;
    }

    public void setExits(Map<String, String> exits) {
        this.exits = exits;
    }
}
