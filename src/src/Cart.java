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

    public JSONObject viewCart(){
        JSONObject cart = new JSONObject();

        return null;
    }

    public void addCartItem(CartItem item){
        cartItems.add(item);
    }

    public void addDiscount(double discount){
        discountTotals.add(discount);

    }

    public void setItemQuantity(String id, int amount){
        for(int i = 0; i < cartItems.size(); i++){
            if(cartItems.get(i).itemId.equals(id)){
                cartItems.get(i).itemQuantity = amount;
                break;
            }
        }
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
