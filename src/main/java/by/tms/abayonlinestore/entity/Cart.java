package by.tms.abayonlinestore.entity;

import by.tms.abayonlinestore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Cart {


    @Autowired
    private CartRepository cartRepository;

    public void addItemToCart(Item item){
        cartRepository.save(item);
    }

    public List<Item> getAllCartItems(){
        return cartRepository.findAll();
    }
}
