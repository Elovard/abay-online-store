package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.Item;
import by.tms.abayonlinestore.entity.ItemCategory;
import by.tms.abayonlinestore.service.ItemService;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private ItemService itemService;

    @PersistenceContext
    private EntityManager entityManager;

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
    public ModelAndView searchName(@RequestParam(value = "itemName", required = false)String itemName,
                                   ModelAndView modelAndView){
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Item> searchResult = searchSession.search(Item.class)
                .where(f -> f.match()
                        .fields("itemName", "itemDesc")
                        .matching(itemName))
                .fetch(20);

        long totalHitCount = searchResult.total().hitCount();
        List<Item> hits = searchResult.hits();

        List<Item> hits2 = searchSession.search(Item.class) // check
                .where(f -> f.match()
                        .fields("itemName", "itemDesc")
                        .matching(itemName))
                .fetchHits(20);
        modelAndView.addObject("searchResult", searchResult);
        modelAndView.addObject("testHits", hits);
        modelAndView.addObject("testHits2", hits2);
        modelAndView.addObject("testTotalHitCount", totalHitCount);

        if(hits.isEmpty()){
            modelAndView.addObject("noItemsFound", "Nothing has been found!");
        }

        modelAndView.setViewName("search");
        entityManager.close();
        return modelAndView;
    }

}
