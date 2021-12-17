
import org.json.simple.JSONObject;

public abstract class Item {
    public String itemId;
    public String name;
    public String description;
    public String image;
    public double price;
    public boolean inStock;

//    public Item(String itemId, String name, String description, String image, double price) {
//        this.itemId = itemId;
//        this.name = name;
//        this.description = description;
//        this.image = image;
//        this.price = price;
//    }

    public JSONObject toJsonObject(){
        JSONObject item = new JSONObject();
        item.put("ID", itemId);
        item.put("Name", name);
        item.put("Description", description);
        item.put("Image", image);
        item.put("Price", price);
        return item;
    }
}
