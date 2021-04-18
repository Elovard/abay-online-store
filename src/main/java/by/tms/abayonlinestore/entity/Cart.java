package by.tms.abayonlinestore.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private float totalPrice;

    private List<Item> cartItems = new ArrayList<>();

    public void addItemToCart(Item item){
        cartItems.add(item);
        totalPrice += item.getItemPrice();
    }

    public void removeItemFromCart(Item item){
        cartItems.remove(item);
        totalPrice = totalPrice - item.getItemPrice();
    }

    public List<Item> getAllItems(){
        return new ArrayList<>(cartItems);
    }

    public void removeAllItems(){
        totalPrice = 0;
        cartItems.clear();
    }

    public float getTotalPrice(){
        return totalPrice;
    }
}
