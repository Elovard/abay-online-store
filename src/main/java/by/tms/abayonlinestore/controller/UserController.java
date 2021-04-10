package by.tms.abayonlinestore.controller;

import by.tms.abayonlinestore.entity.User;
import by.tms.abayonlinestore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/reg")
    public ModelAndView getRegistrationPage(ModelAndView modelAndView) {
        modelAndView.addObject("userForm", new User());
        modelAndView.setViewName("reg");
        return modelAndView;
    }

    @PostMapping("/reg")
    public ModelAndView addUser(@ModelAttribute("userForm") @Valid User user, BindingResult bindingResult, ModelAndView modelAndView) {
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("reg");
            return modelAndView;
        }
        if (!userService.saveUser(user)) {
            modelAndView.addObject("userExistsError", "User with such username already exists!");
            modelAndView.setViewName("reg");
            return modelAndView;
        }
        modelAndView.setViewName("redirect:/user/auth");
        return modelAndView;
    }

    @GetMapping("/auth")
    public ModelAndView getLoginPage(@RequestParam(value = "error", required = false) String error,
                                     @RequestParam(value = "logout", required = false) String logout,
                                     ModelAndView modelAndView) {
        modelAndView.addObject("error", error != null);
        modelAndView.addObject("logout", logout != null);
        modelAndView.setViewName("auth");
        return modelAndView;
    }

}
