import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class CartApi {
    HashMap<String, Cart> carts;
    ArrayList<CartItem> items;

    public CartApi(HashMap<String, Cart> carts) {
        this.carts = carts;
        this.items = new ArrayList<>();
    }

    public JSONObject handleViewCart(String cartID, String state){
        JSONObject viewCart = new JSONObject();
        Cart currentCart = carts.get(cartID);
        double tax;
        if(currentCart != null){
            tax = addTax(currentCart, state);
            currentCart.viewCart(tax);
            viewCart = currentCart.viewCart(tax);
            return viewCart;
        }
        viewCart.put("Status", "Invalid Cart");
        return viewCart;
    }
    public double addTax(Cart cart, String state){
        double tax = 0.0;
        if(state != null) {
            switch (state.toLowerCase()) {
                case "in":
                    tax = cart.discountPrice() * 1.07;
                    break;
                case "il":
                    tax = cart.discountPrice() * 1.06;
                    break;
                case "oh":
                    tax = cart.discountPrice() * 1.05;
                    break;

            }
        }
        else {
            tax = cart.discountPrice();
        }
        return tax;
    }

    public JSONObject handleAddItemToCart(String itemID, int itemQuantity, String cartID){
        JSONObject addItemToCart = new JSONObject();
        Cart currentCart = carts.get(cartID);
        if (currentCart == null){
            addItemToCart.put("Status", "Invalid Cart");
            return addItemToCart;
        }
        for(CartItem item : items){
            if(item.itemId.equals(itemID)){
                if(item.inStock) {
                    if(itemQuantity > 0) {
                        currentCart.addCartItem(item, itemQuantity);
                        addItemToCart.put("Status", "Item Added Successfully");
                        return addItemToCart;
                    }
                }
            }
        }
        addItemToCart.put("Status", "Item does not exist");
        return addItemToCart;
    }
    public JSONObject handleAddDiscount(String userID, String cartID, String discountCode, DiscountManager dm){
        JSONObject addDiscountResponse = new JSONObject();
        DiscountManager discountManager = dm;
        int response = discountManager.addDiscount(userID, discountCode, carts.get(cartID));
        switch (response){
            case 0:
                addDiscountResponse.put("Status", "User out of attempts");
                break;
            case 1:
                addDiscountResponse.put("Status", "Invalid Code");
                break;
            case 2:
                addDiscountResponse.put("Status", "Expired Code");
                break;
            case 3:
                addDiscountResponse.put("Status", "Discount Applied Successfully");
        }

        return addDiscountResponse;
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
            changeItemQuantityResponse.put("Status:", "Item not in Cart");
        }

        return changeItemQuantityResponse;
    }

}

