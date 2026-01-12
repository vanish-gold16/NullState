package Locations;

import Models.Item;
import Models.NPC;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Location {

    private String name;
    private String description;
    private List<Item> locationItems = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private HashMap<String, Location> exits = new HashMap<>();


}
