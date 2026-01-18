package models;

import java.util.List;

public class SaveData {

    private String currentLocationName;
    private int health;
    private int money;
    private int cyberpsychosis;
    private List<String> inventoryItemNames;

    public SaveData() {
    }

    public SaveData(String currentLocationName, int health, int money,
                    int cyberpsychosis, List<String> inventoryItemNames) {
        this.currentLocationName = currentLocationName;
        this.health = health;
        this.money = money;
        this.cyberpsychosis = cyberpsychosis;
        this.inventoryItemNames = inventoryItemNames;
    }

    public String getCurrentLocationName() { return currentLocationName; }
    public void setCurrentLocationName(String currentLocationName) { this.currentLocationName = currentLocationName; }

    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }

    public int getMoney() { return money; }
    public void setMoney(int money) { this.money = money; }

    public int getCyberpsychosis() { return cyberpsychosis; }
    public void setCyberpsychosis(int cyberpsychosis) { this.cyberpsychosis = cyberpsychosis; }

    public List<String> getInventoryItemNames() { return inventoryItemNames; }
    public void setInventoryItemNames(List<String> inventoryItemNames) { this.inventoryItemNames = inventoryItemNames; }
}
