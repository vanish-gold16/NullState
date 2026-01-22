package dao;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import models.NPC;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NPCDAO {

    private Map<String, NPC> npcs = new HashMap<>();

    public NPCDAO() {
        loadNPCsFromFile("src/jsons/npcs.json");
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
            if(npcWrapper == null || npcWrapper.npcs == null){
                return;
            }
            for(NPCData data: npcWrapper.npcs){
                NPC npc = new NPC(
                        data.name,
                        data.description,
                        data.affiliation,
                        null
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
        String name;
        String description;
        String affiliation;
        JsonElement location;
    }

}
