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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
    String list(Model model) {
        auth = SecurityContextHolder.getContext().getAuthentication();
        this.username = (auth.getName() == "anonymousUser")?"not logged in":auth.getName();
        if(username == "not logged in"){
            model.addAttribute("actualRole", "CLIENTE");
        }else{
            model.addAttribute("actualRole", userService.findByUsername(username).getRole());
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
    String save(@Valid Restaurant restaurant, @RequestParam("file") MultipartFile foto,
                BindingResult bindingResult, RedirectAttributes flash) {

        if(!foto.isEmpty()){

            String uniqueFilename = UUID.randomUUID().toString() + "_" + foto.getOriginalFilename();
            Path rootPath = Paths.get("uploads").resolve(uniqueFilename);

            Path rootAbsolutePath = rootPath.toAbsolutePath();

            try{
                Files.copy(foto.getInputStream(),rootAbsolutePath);

                flash.addFlashAttribute("info", "Has subido correctamente '" + uniqueFilename +"'");
                restaurant.setFoto(uniqueFilename);
                restaurantService.saveRestaurant(restaurant);
            }catch (IOException e){
                e.printStackTrace();
            }
        }

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
    String showRestaurant(@PathVariable Integer id, Model model) {
        Restaurant restaurant = restaurantService.getRestaurant(id);
        model.addAttribute("restaurant", restaurant);
        return "showRestaurant";
    }
}
