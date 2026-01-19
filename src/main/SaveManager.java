package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Item;
import models.Player;
import models.SaveData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {

    private static final String SAVE_FILE = "src/jsons/save.json";
    private Gson gson;

    public SaveManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        try{
            Path savePath = Paths.get(SAVE_FILE).toAbsolutePath();
            if(savePath.getParent() != null){
                Files.createDirectories(savePath.getParent());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveGame(Player player, String locationName) {
        List<String> inventoryItems = new ArrayList<>();
        for(Item item : player.getInventory()){
            inventoryItems.add(item.getName());
        }
        SaveData saveData = new SaveData(
                locationName,
                player.getEddie(),
                player.getCyberpsychosisLevel(),
                inventoryItems
        );
        try{
            Files.writeString(Paths.get(SAVE_FILE), gson.toJson(saveData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public SaveData loadGame() {
        // TODO
    }

    public void deleteSave() {
        // TODO
    }

    public boolean saveExists() {
        // TODO
    }

}
