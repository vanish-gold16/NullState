package Models;

public class NPC {

    private String name;
    private String description;
    private Location location;

    public NPC(String name, String description, Location location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    public void interaction(){

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

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
