package models;

public class Item {

    private String name;
    private String description;
    private double weight;
    private String type;
    private int impact;

    public Item(String name, String description, double weight, String type, int impact) {
        this.name = name;
        this.description = description;
        this.weight = weight;
        this.type = type;
        this.impact = impact;
    }

    public int getImpact() {
        return impact;
    }

    public void setImpact(int impact) {
        this.impact = impact;
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

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
