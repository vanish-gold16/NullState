package Models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Item> inventory = new ArrayList<>();
    private int cyberpsychosisLevel;
    private int eddie;

    public Player(List<Item> inventory) {
        this.inventory = inventory;
        this.cyberpsychosisLevel = 20;
        this.eddie = 20000;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        if(inventory.size() <= 2) this.inventory = inventory;
        else System.out.println("Inventory is full");
    }

    public int getCyberpsychosisLevel() {
        return cyberpsychosisLevel;
    }

    public void setCyberpsychosisLevel(int cyberpsychosisLevel) {
        if(cyberpsychosisLevel <= 100 && cyberpsychosisLevel >= 0) this.cyberpsychosisLevel = cyberpsychosisLevel;
        else System.out.println("Invalid cyberpsychosis level");
    }

    public int getEddie() {
        return eddie;
    }

    public void setEddie(int eddie) {
        this.eddie = eddie;
    }
}
