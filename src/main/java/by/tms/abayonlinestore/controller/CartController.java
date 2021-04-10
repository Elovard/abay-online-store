package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.service.CartService;
import by.tms.abayonlinestore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "/cart")
public class CartController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private CartService cartService;

    @GetMapping
    public ModelAndView getCartPage(ModelAndView modelAndView){
        modelAndView.setViewName("/store/item/view/{itemId}");
        return modelAndView;
    }

    @PostMapping("/store/item/view/{itemId}")
    public ModelAndView postCartPage(@PathVariable long itemId, ModelAndView modelAndView, Item item, HttpServletRequest req){
        Item byId = itemService.findItemById(itemId);
        cartService.addToCart(byId);
        modelAndView.setViewName("/store/item/view/{itemId}");
        return modelAndView;
    }
}
