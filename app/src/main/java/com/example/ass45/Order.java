package com.example.ass45;

public class Order {
        private int id;
        private String meals;
        private Double price;
        private Integer qty;
        private String tip;
        private Double total_price;
        private int img;
    public void setImg(int img) {
        this.img = img;
    }
    public String getMeals() {
        return meals;
    }
    public int getImg() {
        return img;
    }
    public Order(String meals, Double price, Integer qty, String tip, Double total_price, int img) {

        this.meals = meals;
        this.price = price;
        this.qty = qty;
        this.tip = tip;
        this.total_price = total_price;
        this.img=img;
    }


    public Order(int id,String meals, Double price, Integer qty, String tip, Double total_price, int img) {
        this.id=id;
        this.meals = meals;
        this.price = price;
        this.qty = qty;
        this.tip = tip;
        this.total_price = total_price;
        this.img=img;
    }
    public int getId() {
        return id;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getTip() {
        return tip;
    }

    @Override
    public String toString() {
        return "Order{" +
                "meals='" + meals + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", tip='" + tip + '\'' +
                ", total_price=" + total_price +
                ", img='" + img + '\'' +
                '}';
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }
}
