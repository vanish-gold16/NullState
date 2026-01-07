package Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Location {

    private String name;
    private String description;
    private List<Item> locationItems = new ArrayList<>();
    private List<NPC> npcs = new ArrayList<>();
    private HashMap<String, Location> exits = new HashMap<>();

}
