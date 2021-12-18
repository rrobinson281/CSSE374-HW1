
import org.json.simple.JSONObject;

public abstract class Item {
    public String itemId;
    public String name;
    public String description;
    public String image;
    public double price;
    public boolean inStock;

    public JSONObject toJsonObject(){
        JSONObject item = new JSONObject();
        item.put("ID", itemId);
        item.put("Name", name);
        item.put("Description", description);
        item.put("Image", image);
        item.put("Price", price);
        item.put("In Stock", inStock);
        return item;
    }
}
