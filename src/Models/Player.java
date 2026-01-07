package Models;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Item> inventory = new ArrayList<>();
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
        if(inventory.size() <= 2) this.inventory = inventory;
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
}
