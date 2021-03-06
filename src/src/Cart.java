import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;

public class Cart {

    String cartId;
    ArrayList<CartItem> cartItems;
    ArrayList<Double> discountTotals;

    public Cart(String cartId, ArrayList<CartItem> cartItems) {
        this.cartId = cartId;
        this.cartItems = cartItems;
        this.discountTotals = new ArrayList<>();
    }


    public JSONObject toJsonObject(){
        JSONObject cart = new JSONObject();
        JSONArray itemArray = new JSONArray();
        for( int i = 0; i < cartItems.size(); i++){
            itemArray.add(cartItems.get(i).toJsonObject());
        }
        cart.put("ID", cartId);
        cart.put("Items", itemArray);

        return cart;
    }

    public JSONObject viewCart(double tax){
        JSONObject cart = new JSONObject();
        cart.put("Cart", this.toJsonObject());
        cart.put("Subtotal", this.getSubtotal());
        cart.put("DiscountPrice", this.discountPrice());
        cart.put("Tax", tax);


        return cart;
    }

    public void addCartItem(CartItem item, int quantity){
        item.itemQuantity = quantity;
        cartItems.add(item);
    }

    public void addDiscount(double discount){
        discountTotals.add(discount);

    }

    public boolean setItemQuantity(String id, int amount){
        for(int i = 0; i < cartItems.size(); i++){
            if(cartItems.get(i).itemId.equals(id)){
                if(amount == 0){
                    cartItems.remove(i);
                    return true;
                }
                cartItems.get(i).itemQuantity = amount;
                return true;
            }
        }
        return false;
    }

    public double getSubtotal(){
        double subtotal = 0;
        for(CartItem item: cartItems){
            subtotal += (item.price * item.itemQuantity);
        }
        return subtotal;
    }

    public double discountPrice(){
        double price = getSubtotal();
        for(Double discount: discountTotals){
            price *= (1 - discount);
        }
        return price;
    }


}
