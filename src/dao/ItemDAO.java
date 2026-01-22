package dao;

import com.google.gson.Gson;
import models.Item;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDAO {

    private Map<String, Item> itemsDatabase = new HashMap<>();

    public ItemDAO() {
        loadItemsFromFile("src/jsons/items.json");
    }

    /**
     * Load items from a file
     * @param filePath the path to the file
     */
    private void loadItemsFromFile(String filePath){
        try{
            String json = new String(Files.readAllBytes(Paths.get(filePath)));
            Gson gson = new Gson();
            ItemsWrapper itemsWrapper = gson.fromJson(json, ItemsWrapper.class);

            if(itemsWrapper == null || itemsWrapper.items == null){
                return;
            }

            for(ItemData data : itemsWrapper.items){
                Item item = new Item(
                        data.name,
                        data.description,
                        data.weight,
                        data.type,
                        data.impact
                );
                itemsDatabase.put(data.name, item);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Get item by name
     * @param name the name of the item
     * @return item
     */
    public Item getItemByName(String name){
        return itemsDatabase.get(name);
    }

    private static class ItemsWrapper{
        List<ItemData> items;
    }

    private static class ItemData{
        String name;
        String description;
        double weight;
        String type;
        int impact;
    }

}
