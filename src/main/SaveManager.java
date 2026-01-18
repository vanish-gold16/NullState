package main;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import models.Player;
import models.SaveData;

public class SaveManager {

    private static final String SAVE_FILE = "src/jsons/save.json";
    private Gson gson;

    public SaveManager() {
        gson = new GsonBuilder().setPrettyPrinting().create();
        // TODO
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
