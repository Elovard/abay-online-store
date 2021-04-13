package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping(path = "/admin")
public class AdminPanelController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ModelAndView getAdminPanel(ModelAndView modelAndView){
        modelAndView.addObject("newItem", new Item());
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping("/add")
    public ModelAndView addItemByAdmin(@ModelAttribute("newItem")Item newItem,  ModelAndView modelAndView){
        Item item = new Item();
        item.setItemId(newItem.getItemId());
        item.setItemName(newItem.getItemName());
        item.setItemDesc(newItem.getItemDesc());
        item.setItemPrice(newItem.getItemPrice());
        item.setPicLink(newItem.getPicLink());
        itemService.addItem(item);
        modelAndView.setViewName("admin");
        return modelAndView;
    }

    @PostMapping("/remove")
    public ModelAndView removeItemByAdmin(long itemId, ModelAndView modelAndView){
        if(itemService.isItemExistsById(itemId)){
            itemService.removeItemById(itemId);
        } else {
            modelAndView.addObject("itemIsNotExist", "Can't find item with this id!");
        }
        modelAndView.setViewName("admin");
        return modelAndView;
    }
}
