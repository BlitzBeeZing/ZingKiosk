package app.zingnow.kiosk.model;

import java.io.Serializable;

public class Menu implements Serializable {

    String id;
    String name;
    String photo;
    String price;
    String restaurant_name;
    Boolean veg;



    int quantity;



    Boolean isSelected;

    public Menu(String id, String name, String photo, String price, String restaurant_name, Boolean veg, Boolean isSelected,int quantity) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.price = price;
        this.restaurant_name = restaurant_name;
        this.veg = veg;
        this.isSelected = isSelected;
        this.quantity = quantity;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public Boolean getVeg() {
        return veg;
    }

    public void setVeg(Boolean veg) {
        this.veg = veg;
    }

    public Boolean getSelected() {
        return isSelected;
    }

    public void setSelected(Boolean selected) {
        isSelected = selected;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}
