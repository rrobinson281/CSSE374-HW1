import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class CartApi {
    HashMap<String, Cart> carts;

    public CartApi(HashMap<String, Cart> carts) {
        this.carts = carts;
    }

    public JSONObject handleViewCart( ){
        return null;
    }
    public JSONObject handleAddItemToCart(){
        return null;
    }
    public JSONObject handleAddDiscount(String userID, String cartID, String discountCode){

        return null;
    }
    public JSONObject handleChangeItemQuantity(String cartId, String itemId, int updatedQuantity){
        JSONObject changeItemQuantityResponse = new JSONObject();

        boolean result = carts.get(cartId).setItemQuantity(itemId, updatedQuantity);
        if(result && updatedQuantity == 0){
            changeItemQuantityResponse.put("Status:", "Item Removed From Cart");
        }
        else if(result){
            changeItemQuantityResponse.put("Status:", "Item Quantity Changed");
        }
        else{
            changeItemQuantityResponse.put("Status:", "Failed to update quantity");
        }

        return changeItemQuantityResponse;
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

