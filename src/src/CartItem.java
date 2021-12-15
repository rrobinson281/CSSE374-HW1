public class CartItem extends Item {

    private int itemQuantity;

    public CartItem(String itemId, String name, String description, String image, double price, int quantity) {
        this.itemId = itemId;
            this.name = name;
            this.description = description;
            this.image = image;
            this.price = price;
            this.itemQuantity = quantity;
    }
}
