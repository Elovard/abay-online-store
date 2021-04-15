package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Cart;
import by.tms.abayonlinestore.entity.Order;
import by.tms.abayonlinestore.entity.OrderStatus;
import by.tms.abayonlinestore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @GetMapping
    public ModelAndView getOrderPage(ModelAndView modelAndView, HttpSession httpSession){
        modelAndView.addObject("newOrder", new Order());
        Cart cart = (Cart)httpSession.getAttribute("cart");
        if(!cart.getAllItems().isEmpty()){
            modelAndView.addObject("listOfItems", "Items in your cart:");
            modelAndView.addObject("cartItems", cart.getAllItems());
        } else {
            modelAndView.addObject("cartIsEmpty", "Your cart is empty!");
        }
        modelAndView.setViewName("order");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postOrderPage(@ModelAttribute("newOrder") @Valid Order newOrder,
                                      ModelAndView modelAndView, BindingResult bindingResult,
                                      HttpSession httpSession){
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("order");
            modelAndView.addObject("errorsInOrder", "Check the inputs below!");
            return modelAndView;
        }
            Order order = new Order();
            order.setOrderedBy(newOrder.getOrderedBy());
            order.setMobilePhone(newOrder.getMobilePhone());
            order.setAddress(newOrder.getAddress());
            order.setCity(newOrder.getCity());
            order.setAddress(newOrder.getAddress());
            order.setCreditCardNumber(newOrder.getCreditCardNumber());
            order.setCcExpiration(newOrder.getCcExpiration());
            order.setCcCVV(newOrder.getCcCVV());
            order.setCreatedAt(LocalDateTime.now());
            order.setOrderStatus(OrderStatus.UNDERWAY);
            orderService.placeOrder(order);
            modelAndView.setViewName("redirect:/order/success");
            Cart cart = (Cart)httpSession.getAttribute("cart");
            cart.removeAllItems();
            return modelAndView;
    }

    @GetMapping("/success")
    public ModelAndView getSuccessPage(ModelAndView modelAndView){
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
