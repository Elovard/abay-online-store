package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.entity.ItemCategory;
import by.tms.abayonlinestore.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public ModelAndView getSearchPage(ModelAndView modelAndView){
        modelAndView.setViewName("search");
        return modelAndView;
    }

    @PostMapping
    public ModelAndView postSearchByCategoryPage(@RequestParam(value = "itemCategory", required = false)String itemCategory,
                                                 ModelAndView modelAndView){
        try {
            String resultInEnum = itemCategory.toUpperCase();
            List<Item> result = itemService.searchItemByCategory(ItemCategory.valueOf(resultInEnum));
            if(result.isEmpty()){
                modelAndView.addObject("nothingFound", "Nothing has been found!");
            }
            modelAndView.addObject("result", result);
            modelAndView.setViewName("search");
        } catch (IllegalArgumentException ex) {
            modelAndView.addObject("nothingFound", "Nothing has been found!");
            modelAndView.setViewName("search");
        }
        return modelAndView;
    }

    @PostMapping("/name")
    public ModelAndView postSearchByNamePage(@RequestParam(value = "itemName", required = false)String itemName,
                                             ModelAndView modelAndView){

        List<Item> result = itemService.searchItemsByName(itemName);
        if(result.isEmpty()){
            modelAndView.addObject("nothingFoundByName", "Nothing has been found!");
        }
        modelAndView.addObject("result", result);
        modelAndView.setViewName("search");
        return modelAndView;
    }
}
