import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.TestCase.assertEquals;

public class TestApi {

    @Test
    public void testCartItemJson(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.89, 2, true);
        JSONObject result = cartItem.toJsonObject();
        JSONObject expected = new JSONObject();
        expected.put("ID", "1");
        expected.put("Name", "Sprite");
        expected.put("Description", "16 oz can");
        expected.put("Image", "img");
        expected.put("Price", 1.89);
        expected.put("Quantity", 2);
        expected.put("In Stock", true);
        assertEquals(expected,result);
    }

    @Test
    public void testCartJson(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.89, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.89, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);
        JSONObject result = new JSONObject();
        result = cart.toJsonObject();

        JSONObject expected = new JSONObject();
        JSONObject expectedItem = new JSONObject();
        expectedItem.put("ID", "1");
        expectedItem.put("Name", "Sprite");
        expectedItem.put("Description", "16 oz can");
        expectedItem.put("Image", "img");
        expectedItem.put("Price", 1.89);
        expectedItem.put("Quantity", 2);
        expectedItem.put("In Stock", true);

        JSONObject expectedItem1 = new JSONObject();
        expectedItem1.put("ID", "2");
        expectedItem1.put("Name", "Pepsi");
        expectedItem1.put("Description", "16 oz can");
        expectedItem1.put("Image", "img");
        expectedItem1.put("Price", 2.89);
        expectedItem1.put("Quantity", 1);
        expectedItem1.put("In Stock", true);

        JSONArray expectedItemList = new JSONArray();
        expectedItemList.add(expectedItem);
        expectedItemList.add(expectedItem1);

        expected.put("ID", "Cart1");
        expected.put("Items", expectedItemList);

        assertEquals(expected, result);
    }

    @Test
    public void testViewCart(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);
        cart.discountTotals.add(0.25);
        JSONObject result = new JSONObject();
        result = cart.viewCart(3.21);

        JSONObject expected = new JSONObject();
        JSONObject expectedItem = new JSONObject();
        expectedItem.put("ID", "1");
        expectedItem.put("Name", "Sprite");
        expectedItem.put("Description", "16 oz can");
        expectedItem.put("Image", "img");
        expectedItem.put("Price", 1.00);
        expectedItem.put("Quantity", 2);
        expectedItem.put("In Stock", true);

        JSONObject expectedItem1 = new JSONObject();
        expectedItem1.put("ID", "2");
        expectedItem1.put("Name", "Pepsi");
        expectedItem1.put("Description", "16 oz can");
        expectedItem1.put("Image", "img");
        expectedItem1.put("Price", 2.00);
        expectedItem1.put("Quantity", 1);
        expectedItem1.put("In Stock", true);

        JSONArray expectedItemList = new JSONArray();
        expectedItemList.add(expectedItem);
        expectedItemList.add(expectedItem1);

        JSONObject expected2 = new JSONObject();
        expected2.put("ID", "Cart1");
        expected2.put("Items", expectedItemList);

        expected. put("Cart", expected2);
        expected.put("Subtotal", 4.00);
        expected.put("DiscountPrice", 3.00);
        expected.put("Tax", 3.21);

        assertEquals(expected, result);

    }

    @Test
    public void testSetItemQuantityToZero(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);
        boolean result = cart.setItemQuantity("2", 0);

        assertEquals(true, result);
    }

    @Test
    public void testSetItemQuantity(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);
        boolean result = cart.setItemQuantity("2", 3);

        assertEquals(true, result);
    }

    @Test
    public void testGetSubtotal(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);
        double result = cart.getSubtotal();

        assertEquals(4.0, result);

    }

    @Test
    public void testDiscountPrice(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);
        cart.discountTotals.add(0.25);
        double result = cart.discountPrice();

        assertEquals(3.0, result);
    }

    @Test
    public void testDiscManBannedUser(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);

        DiscountManager discountManager = new DiscountManager();
        discountManager.userDiscountAttempts.put("user1", 5);
        int result = discountManager.addDiscount("user1", "disc1", cart);

        assertEquals(0,result);
    }

    @Test
    public void testDiscManInvalidCode(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);

        DiscountManager discountManager = new DiscountManager();
        discountManager.userDiscountAttempts.put("user1", 1);
        int result = discountManager.addDiscount("user1", "invalidDisc1", cart);

        assertEquals(1, result);
    }

    @Test
    public void testDiscManExpiredCode(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);

        DiscountManager discountManager = new DiscountManager();
        discountManager.userDiscountAttempts.put("user1", 1);
        discountManager.expireDiscountList.add("expDisc1");
        int result = discountManager.addDiscount("user1", "expDisc1", cart);

        assertEquals(2, result);
    }

    @Test
    public void testDiscManValidCode(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);

        DiscountManager discountManager = new DiscountManager();
        discountManager.userDiscountAttempts.put("user1", 1);
        discountManager.discountList.put("goodDisc1", 0.50);
        int result = discountManager.addDiscount("user1", "goodDisc1", cart);

        assertEquals(3, result);
    }

    @Test
    public void testAddTaxIN(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);

        HashMap<String, Cart> cartList = new HashMap<>();
        cartList.put("Cart1", cart);
        CartApi cartApi = new CartApi(cartList);
        double result = cartApi.addTax(cart, "IN");

        assertEquals(4.28, result);
    }

    @Test
    public void testAddTaxNullState(){
        CartItem cartItem = new CartItem("1", "Sprite", "16 oz can", "img", 1.00, 2, true);
        CartItem cartItem1 = new CartItem("2", "Pepsi", "16 oz can", "img", 2.00, 1, true);
        ArrayList<CartItem> itemList = new ArrayList<>();
        itemList.add(cartItem);
        itemList.add(cartItem1);
        Cart cart = new Cart("Cart1", itemList);

        HashMap<String, Cart> cartList = new HashMap<>();
        cartList.put("Cart1", cart);
        CartApi cartApi = new CartApi(cartList);
        double result = cartApi.addTax(cart, null); //simulate guest carts with no state.

        assertEquals(4.00, result);
    }


//    public static void main(String[] args) {
//        System.out.println("hello");
////        testCartItem();
//    }
}
