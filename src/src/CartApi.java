import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CartApi {
    HashMap<String, Cart> carts;

    public JSONObject handleViewCart( ){
        return null;
    }
    public JSONObject handleAddItemToCart(){
        return null;
    }
    public JSONObject handleAddDiscount(){
        return null;
    }
    public JSONObject handleChangeItemQuantity(String cartId, String itemId, int updatedQuantity){
        return null;
    }



    public static void main(String[] args) {
        System.out.println("hello");
//        CartItem cartItem = new CartItem("1", "Cheese", "Yum", "img", 1.2, 1);
//        CartItem cartItem2 = new CartItem("2", "Apple", "Yummy", "img", 0.2, 2);
//        ArrayList<Item> itemList = new ArrayList<>();
//        itemList.add(cartItem);
//        itemList.add(cartItem2);
//        Cart cart = new Cart("2", 50.0, itemList);
//        System.out.println(cart.toJsonObject());
    }
}

