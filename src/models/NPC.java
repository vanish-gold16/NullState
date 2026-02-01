package models;

import dao.NPCDAO;

import java.util.Objects;
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
    public String randomInteraction(NPCDAO npcdao){
        if(Objects.equals(name, npcdao.getNPCByName("Bestie").name)){
            return "Hey, I'm busy now. Talk to me later";
        }
        else if(Objects.equals(name, npcdao.getNPCByName("Viktor").name)){
            return "VEX, go and do something with your biochip! Now!";
        }
        else if(Objects.equals(name, npcdao.getNPCByName("Mike Kowalski").name)){
            return "Good luck with your work!";
        }
        else if(Objects.equals(name, npcdao.getNPCByName("Bezdomovec").name)){
            int replica = random.nextInt(1, 4);
            if(replica == 1){
                return "Spare some credits for a poor soul";
            }
            else if(replica == 2){
                return "Hard path awaits you...";
            }
            else{
                return "Life is tough in this city";
            }
        }
        else if(isEnemyByName()){
            int replica = random.nextInt(1, 4);
            if(replica == 1){
                return "You'll regret coming here!";
            }
            else if(replica == 2){
                return "This is our territory!";
            }
            else{
                return "Prepare to fight!";
            }
        }
        else{
            return "...";
        }
    }

    private boolean isEnemyByName(){
        return Objects.equals(name, "Strážce Arasaky - Metro")
                || Objects.equals(name, "Strážce Arasaky - Koncovka")
                || Objects.equals(name, "Strážce Bergestu - Tunely")
                || Objects.equals(name, "Strážce Bergestu - Dogtown");
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
