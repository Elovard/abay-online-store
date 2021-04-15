package by.tms.abayonlinestore.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {

    private List<Item> cartItems = new ArrayList<>();

    public void addItemToCart(Item item){
        cartItems.add(item);
    }

    public void removeItemFromCart(Item item){
        cartItems.remove(item);
    }

    public List<Item> getAllItems(){
        return new ArrayList<>(cartItems);
    }

    public void removeAllItems(){
        cartItems.clear();
    }
}
