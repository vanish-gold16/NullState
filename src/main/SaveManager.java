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

    private static final String SAVE_FILE = "jsons/save.json";
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

    /**
     * Serializes and writes the current game state to the save file.
     * @param player active player
     * @param locationName current location name
     * @param bestieDialogueCompleted whether Bestie story gate is unlocked
     */
    public void saveGame(Player player, String locationName, boolean bestieDialogueCompleted) {
        List<String> inventoryItems = new ArrayList<>();
        for(Item item : player.getInventory()){
            inventoryItems.add(item.getName());
        }
        SaveData saveData = new SaveData(
                locationName,
                player.getEddie(),
                player.getCyberpsychosisLevel(),
                inventoryItems,
                bestieDialogueCompleted
        );
        try{
            Files.writeString(Paths.get(SAVE_FILE), gson.toJson(saveData));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Loads saved game from disk.
     * @return parsed save data, or null when save does not exist
     */
    public SaveData loadGame() {
        if(!saveExists()){
            return null;
        }
        try{
            String json = Files.readString(Paths.get(SAVE_FILE));
            return gson.fromJson(json, SaveData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Deletes the save file if it exists.
     */
    public void deleteSave() {
        try{
            Files.deleteIfExists(Paths.get(SAVE_FILE));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if a save file exists.
     *
     * @return true when save file is present
     */
    public boolean saveExists() {
        return Files.exists(Paths.get(SAVE_FILE));
    }

}
