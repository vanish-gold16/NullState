package Models;

import ENUMs.NPCs;

import java.util.Random;

public class NPC {

    private Random random = new Random();

    private NPCs currentNPC;
    private String description;
    private Location location;

    public NPC(NPCs currentNPC, String description, Location location) {
        this.currentNPC = currentNPC;
        this.description = description;
        this.location = location;
    }

    /**
     * interaction patterns for non-written dialogues
     */
    public void interaction(){
        if(currentNPC == NPCs.BESTIE){
            System.out.println("Hey, I'm busy now. Talk to me later");
        }
        else if(currentNPC == NPCs.VIKTOR){
            System.out.println("VEX, go and do something with your biochip! Now!");
        }
        else if(currentNPC == NPCs.KOWALSKI){
            System.out.println("Good luck with your work!");
        }
        else if(currentNPC == NPCs.BEZDOMOVEC){
            int replica = random.nextInt(1, 4);
            if(replica == 1){
                System.out.println("Spare some credits for a poor soul");
            }
            else if(replica == 2){
                System.out.println("Hard path awaits you...");
            }
            else{
                System.out.println("Life is tough in this city");
            }
        }
        else if(currentNPC == NPCs.ENEMIES){
            int replica = random.nextInt(1, 4);
            if(replica == 1){
                System.out.println("You'll regret coming here!");
            }
            else if(replica == 2){
                System.out.println("This is our territory!");
            }
            else{
                System.out.println("Prepare to fight!");
            }
        }
        else{
            System.out.println("...");
        }
    }

    public NPCs currentNPC() {
        return currentNPC;
    }

    public void setCurrentNPC(NPCs currentNPC) {
        this.currentNPC = currentNPC;
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
