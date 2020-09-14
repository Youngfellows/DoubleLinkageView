package example.com.shoppingcart;

/**
 * Created by ZZN on 2017/9/26.
 */

public class Results {
    private String merchant;
    private String foodclass;
    private String foodname;
    private String foodimg;
    private String foodprice;

    public String getMerchant() {
        return merchant;
    }

    public void setMerchant(String merchant) {
        this.merchant = merchant;
    }

    public String getFoodclass() {
        return foodclass;
    }

    public String getFoodimg() {
        return foodimg;
    }

    public String getFoodname() {
        return foodname;
    }

    public String getFoodprice() {
        return foodprice;
    }

    public void setFoodclass(String foodclass) {
        this.foodclass = foodclass;
    }

    public void setFoodimg(String foodimg) {
        this.foodimg = foodimg;
    }

    public void setFoodname(String foodname) {
        this.foodname = foodname;
    }

    public void setFoodprice(String foodprice) {
        this.foodprice = foodprice;
    }

}
