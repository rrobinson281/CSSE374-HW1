import org.json.simple.JSONObject;

public class CartItem extends Item {

    int itemQuantity;

    public CartItem(String itemId, String name, String description, String image, double price, int quantity, boolean inStock) {
        this.itemId = itemId;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.itemQuantity = quantity;
        this.inStock = inStock;
    }

    public JSONObject toJsonObject() {
        JSONObject cartItem = super.toJsonObject();
        cartItem.put("Quantity", itemQuantity);
        return cartItem;
    }
}
