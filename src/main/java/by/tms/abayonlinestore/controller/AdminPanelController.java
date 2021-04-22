package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.service.ItemService;
import by.tms.abayonlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(path = "/admin")
public class AdminPanelController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @GetMapping
    public ModelAndView getAdminPanel(ModelAndView modelAndView){
        modelAndView.addObject("newItem", new Item());
        modelAndView.setViewName("admin/admin");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addItemByAdmin(@ModelAttribute("newItem")Item newItem, BindingResult bindingResult,
                                       ModelAndView modelAndView){
        if(bindingResult.hasErrors()){
            modelAndView.setViewName("admin/admin");
            modelAndView.addObject("cantAdd", "Error occurred while adding the item! (check category)");
            return modelAndView;
        }
        Item item = new Item();
        item.setItemName(newItem.getItemName());
        item.setItemDesc(newItem.getItemDesc());
        item.setItemPrice(newItem.getItemPrice());
        item.setItemCategory(newItem.getItemCategory());
        item.setPicLink(newItem.getPicLink());
        itemService.addItem(item);
        modelAndView.addObject("addedItem", "Item has been added!");
        modelAndView.setViewName("admin/admin");
        return modelAndView;
    }

    @GetMapping("/remove")
    public ModelAndView getDelPage(ModelAndView modelAndView){
        modelAndView.setViewName("admin/adminRemove");
        return modelAndView;
    }


    @PostMapping("/remove")
    public ModelAndView removeItemByAdmin(@RequestParam(value = "itemId", required = false)Long itemId, ModelAndView modelAndView){
        if(itemService.isItemExistsById(itemId)){
            itemService.removeItemById(itemId);
            modelAndView.addObject("itemRemoved", "Item has been removed");
        } else {
            modelAndView.addObject("itemIsNotExist", "Can't find item with this id!");
        }
        modelAndView.setViewName("admin/adminRemove");
        return modelAndView;
    }

    @GetMapping("/users")
    public ModelAndView getUsersPage(ModelAndView modelAndView){
        modelAndView.setViewName("admin/adminCustomers");
        modelAndView.addObject("users", userService.allUsers());
        return modelAndView;
    }
}
