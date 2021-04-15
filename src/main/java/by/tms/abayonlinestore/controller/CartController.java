package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Cart;
import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ModelAndView getCart(ModelAndView modelAndView, HttpSession httpSession){
        Cart cart = (Cart)httpSession.getAttribute("cart");
        if(!cart.getAllItems().isEmpty()){
            modelAndView.addObject("cartItems", cart.getAllItems());
        } else {
            modelAndView.addObject("cartIsEmpty", "Your cart is empty");
        }
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView cartAction(@RequestParam(value = "itemId", required = false)Long itemId,
                                   ModelAndView modelAndView, HttpSession httpSession){
        Item byId = itemService.findItemById(itemId);
        Cart cart = (Cart)httpSession.getAttribute("cart");
        cart.addItemToCart(byId);
        modelAndView.setViewName("redirect:/store/item/view/" + itemId);
        return modelAndView;
    }

}
