package dao;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.DialogNode;

import java.awt.*;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class DialogDAO {

    private Map<String, Map<String, DialogNode>> allDialogs;

    public DialogDAO() {
        loadDialogs();
    }

    private void loadDialogs(){
        Gson gson = new Gson();
        InputStream inputStream = getClass().getResourceAsStream("/jsons/dialogs.json");

        if(inputStream == null){
            allDialogs = new HashMap<>();
            return;
        }

        Type type = new TypeToken<Map<String, Map<String, DialogNode>>>() {}.getType();
        allDialogs = gson.fromJson(new InputStreamReader(inputStream), type);
    }

    public Map<String, DialogNode> getDialogForNPC(String NPCName){
        return allDialogs.get(NPCName.toLowerCase());
    }

}
