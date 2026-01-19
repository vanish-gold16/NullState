package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Player;
import models.SaveData;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
        // TODO
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
