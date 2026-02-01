package models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Item> inventory = new ArrayList<>();
    private static final int MAX_SLOTS = 2;
    private int cyberpsychosisLevel;
    private int eddie;

    public void increaseCyberpsychosis(int amount) throws InterruptedException {
        if(amount > 0){
            setCyberpsychosisLevel(this.cyberpsychosisLevel + amount);
        }
        gameOver();
    }

    public void gameOver() throws InterruptedException {
        if(this.cyberpsychosisLevel >= 100){
            System.out.println("Vex got cyberpsychosis.");
            Thread.sleep(2000);
            System.out.println("Game Over.");
            System.exit(0);
        }
    }

    public Player(List<Item> inventory) {
        this.inventory = inventory;
        this.cyberpsychosisLevel = 20;
        this.eddie = 20000;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        if(inventory.size() <= MAX_SLOTS) this.inventory = inventory;
        else System.out.println("Inventory is full");
    }

    public int getCyberpsychosisLevel() {
        return cyberpsychosisLevel;
    }

    public void setCyberpsychosisLevel(int cyberpsychosisLevel) {
        this.cyberpsychosisLevel = cyberpsychosisLevel;
    }

    public int getEddie() {
        return eddie;
    }

    public void setEddie(int eddie) {
        this.eddie = eddie;
    }

    public int getMaxSlots() {
        return MAX_SLOTS;
    }

    public int getUsedSlots() {
        return inventory.size();
    }

    public boolean isInventoryFull() {
        return inventory.size() >= MAX_SLOTS;
    }

    public boolean addItem(Item item) {
        if(item == null){
            return false;
        }
        if(isInventoryFull()){
            return false;
        }
        inventory.add(item);
        return true;
    }

    public Item removeItemByName(String name) {
        if(name == null || name.isBlank()){
            return null;
        }
        for(int i = 0; i < inventory.size(); i++){
            Item item = inventory.get(i);
            if(item.getName() != null && item.getName().equalsIgnoreCase(name)){
                inventory.remove(i);
                return item;
            }
        }
        return null;
    }

    public double getTotalWeight() {
        double total = 0.0;
        for(Item item : inventory){
            total += item.getWeight();
        }
        return total;
    }

    public String formatInventory() {
        if(inventory.isEmpty()){
            return "Inventar je prazdny.";
        }
        StringBuilder sb = new StringBuilder("Inventar (" + getUsedSlots() + "/" + getMaxSlots() + "):\n");
        for(Item item : inventory){
            sb.append("- ").append(item.getName());
            sb.append(" (").append(item.getType()).append(", ").append(item.getWeight()).append(" kg)\n");
        }
        sb.append("Celkova vaha: ").append(getTotalWeight()).append(" kg");
        return sb.toString();
    }
}
