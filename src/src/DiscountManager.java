import java.util.ArrayList;
import java.util.HashMap;

public class DiscountManager {
    ArrayList<String> expireDiscountList;
    HashMap<String, Integer> userDiscountAttempts;
    HashMap<String, Double> discountList;

    public DiscountManager() {
        expireDiscountList = new ArrayList<>();
        userDiscountAttempts = new HashMap<>();
        discountList = new HashMap<>();
    }

    public int addDiscount(String userID, String discountId, Cart cart){
        Double discount = discountList.get(discountId);
        Integer attempts = userDiscountAttempts.get(userID);
        if(attempts != null && attempts >= 5){
            System.out.println("banned gg ez");
            return 0; // User can not make anymore attempts
        }
        if(discount == null){
            if(userID != null){
                if(attempts == null){
                    userDiscountAttempts.put(userID, 1);
                }
                else{
                    userDiscountAttempts.put(userID, attempts + 1);
                }
            }
            if(!expireDiscountList.contains(discountId)) {
                System.out.println("Invalid code");
                return 1; // user input an invalid
            }
            System.out.println("expired code");
            return 2; // user input an expired code
        }
        cart.addDiscount(discount);
        return 3; //valid code
    }
}
