package com.ucbcba.demo.Controllers;

import com.ucbcba.demo.Entities.Restaurant;
import com.ucbcba.demo.services.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RestaurantController {
    private RestaurantService restaurantService;

    @Autowired
    public void setRestaurantService(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }

    @RequestMapping("/newRestaurant")
    String newRestaurant(Model model) {
        return "newRestaurant";
    }

    @RequestMapping("/home")
    String home(Model model) {
        model.addAttribute("restaurants", restaurantService.listAllRestaurants());
        return "home";
    }

    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    String save(Restaurant restaurant) {
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/home";
    }
}
