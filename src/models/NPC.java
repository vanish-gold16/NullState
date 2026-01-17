package models;

import dao.NPCDAO;

import java.util.Random;

public class NPC {

    private Random random = new Random();

    private String name;
    private String description;
    private String affiliation;
    private Location location;

    public NPC(String name, String description, String affiliation, Location location) {
        this.name = name;
        this.description = description;
        this.affiliation = affiliation;
        this.location = location;
    }

    /**
     * interaction patterns for non-written dialogues
     */
    public void randomInteraction(NPCDAO npcdao){
        if(name == npcdao.getNPCByName("Bestie").name){
            System.out.println("Hey, I'm busy now. Talk to me later");
        }
        else if(name == npcdao.getNPCByName("Viktor").name){
            System.out.println("VEX, go and do something with your biochip! Now!");
        }
        else if(name == npcdao.getNPCByName("Kowalski").name){
            System.out.println("Good luck with your work!");
        }
        else if(name == npcdao.getNPCByName("Bezdomovec").name){
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
        else if(name == npcdao.getNPCByName("Enemies").name){
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

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
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
