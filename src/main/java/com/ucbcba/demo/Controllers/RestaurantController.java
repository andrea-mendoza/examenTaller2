package com.ucbcba.demo.Controllers;

import com.ucbcba.demo.Entities.Restaurant;

import com.ucbcba.demo.Entities.User;
import com.ucbcba.demo.services.CategoryService;
import com.ucbcba.demo.services.CityService;
import com.ucbcba.demo.services.RestaurantService;
import com.ucbcba.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Controller
public class RestaurantController {
    private RestaurantService restaurantService;
    private CityService cityService;
    private CategoryService categoryService;
    private UserService userService;
    private Authentication auth;
    private String username;


    @Autowired
    public void setRestaurantService(RestaurantService restaurantService){
        this.restaurantService = restaurantService;
    }
    @Autowired
    public void setCityService(CityService cityService){
        this.cityService = cityService;
    }
    @Autowired
    public void setUserService(UserService userService){
        this.userService = userService;
    }
    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }
    @RequestMapping("/")
    String home(Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        this.username = (auth.getName() == "anonymousUser")?"not logged in":auth.getName();
        model.addAttribute("cities", cityService.listAllCities());
        model.addAttribute("username", this.username);
        return "home";
    }
    @RequestMapping("/newRestaurant")
    String newRestaurant(Model model) {
        model.addAttribute("restaurant",new Restaurant());
        model.addAttribute( "categories", categoryService.listAllCategories());
        model.addAttribute("cities", cityService.listAllCities());
        return "newRestaurant";
    }
    @RequestMapping("/restaurants")
    String list(Model model) throws UnsupportedEncodingException {
        auth = SecurityContextHolder.getContext().getAuthentication();
        this.username = (auth.getName() == "anonymousUser")?"not logged in":auth.getName();
        if(username == "not logged in"){
            model.addAttribute("actualRole", "CLIENTE");
        }else{
            model.addAttribute("actualRole", userService.findByUsername(username).getRole());
        }
        byte[] bytes;
        String fot;
        List<Restaurant> restaurantIterable = (List<Restaurant>)restaurantService.listAllRestaurants();
        for(int i=0; i<restaurantIterable.size(); i++){
            bytes = Base64.encode(restaurantIterable.get(i).getFoto());
            fot = new String(bytes,"UTF-8");
            restaurantIterable.get(i).setF(fot);
        }
        model.addAttribute("restaurants", restaurantService.listAllRestaurants());
        return "restaurants";
    }
    @RequestMapping("/deleteRestaurant/{id}")
    String delete(@PathVariable Integer id) {
        restaurantService.deleteRestaurant(id);
        return "redirect:/restaurants";

    }
    @RequestMapping(value = "/restaurant", method = RequestMethod.POST)
    String save(Restaurant restaurant, @RequestParam("file") MultipartFile files) throws IOException {
        byte[] f;
        if(!files.isEmpty()){
            f = files.getBytes();
            restaurant.setFoto(f);
        }
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/restaurants";
    }
    @RequestMapping("/editRestaurant/{id}")
    String editRestaurant(@PathVariable Integer id, Model model) {
        model.addAttribute("restaurant", restaurantService.getRestaurant(id));
        model.addAttribute("categories", categoryService.listAllCategories());
        model.addAttribute("cities", cityService.listAllCities());
        return "editRestaurant";
    }
    @RequestMapping("/showRestaurant/{id}")
    String showRestaurant(@PathVariable Integer id, Model model) throws UnsupportedEncodingException {
        auth = SecurityContextHolder.getContext().getAuthentication();
        this.username = (auth.getName() == "anonymousUser")?"not logged in":auth.getName();
        Restaurant restaurant = restaurantService.getRestaurant(id);
        byte[] bytes;
        String fot;
        bytes = Base64.encode(restaurant.getFoto());
        fot = new String(bytes,"UTF-8");
        //restaurant.setF(fot);
        model.addAttribute("restaurant", restaurant);
        model.addAttribute("user", userService.findByUsername(this.username));
        return "showRestaurant";
    }
}
