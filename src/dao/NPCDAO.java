package dao;

import ENUMs.NPCs;
import com.google.gson.Gson;
import models.Location;
import models.NPC;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCDAO {

    private Map<String, NPC> npcs = new HashMap<>();

    public NPCDAO() {
        loadNPCsFromFile("npcs.json");
    }

    /**
     * Load NPCs from a file
     * @param filePath the path to the file
     */
    private void loadNPCsFromFile(String filePath){
        try{
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Gson gson = new Gson();
            NPCWrapper npcWrapper = gson.fromJson(json,NPCWrapper.class);
            for(NPCData data: npcWrapper.npcs){
                NPC npc = new NPC(
                        NPCs.valueOf(data.name.toUpperCase()),
                        "data.description",
                        "data.affiliation",
                        "data.location"
                );
                npcs.put(data.name, npc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public NPC getNPCByName(String name){
        return npcs.get(name);
    }

    private static class NPCWrapper{
        List<NPCData> npcs;
    }

    private static class NPCData{
        NPC npc;
        String description;
        String affiliation;
        Location location;
    }

}
