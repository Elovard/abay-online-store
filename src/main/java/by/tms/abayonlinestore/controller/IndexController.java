package by.tms.abayonlinestore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    @GetMapping("/")
    public ModelAndView getHomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
