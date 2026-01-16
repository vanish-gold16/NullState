package dao;

import com.google.gson.Gson;
import models.NPC;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class NPCDAO {

    private Map<String, NPC> npcs = new HashMap<>();

    public NPCDAO() {
        loadNPCsFromFile();
    }

    /**
     * Load NPCs from a file
     * @param filePath the path to the file
     */
    private void loadNPCsFromFile(String filePath){
        try{
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Gson gson = new Gson();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public NPC getNPCByName(String name){
        return npcs.get(name);
    }

    private static class NPCWrapper{

    }

    private static class NPCData{
        String name;
        String description;
    }

}
