package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ModelAndView getStorePage(ModelAndView modelAndView){
        modelAndView.addObject("items", itemService.getAllItems());
        modelAndView.setViewName("store");
        return modelAndView;
    }

    @GetMapping(path = "/item/view/{itemId}")
    public ModelAndView getItemViewPage(@PathVariable long itemId, ModelAndView modelAndView){
        Item id = itemService.findItemById(itemId);
        modelAndView.addObject("item", id);
        modelAndView.setViewName("item");
        return modelAndView;
    }
}
