package com.ucbcba.demo.Controllers;

import com.ucbcba.demo.Entities.City;
import com.ucbcba.demo.Entities.Restaurant;

import com.ucbcba.demo.Entities.User;
import com.ucbcba.demo.services.CategoryService;
import com.ucbcba.demo.services.CityService;
import com.ucbcba.demo.services.RestaurantService;
import com.ucbcba.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    private String actualRole = "admin";

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
        model.addAttribute("cities", cityService.listAllCities());
        model.addAttribute("actualRole", this.actualRole);
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
        model.addAttribute("actualRole", this.actualRole);
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

    public void setActualRole(String actualRole) {
        this.actualRole = actualRole;
    }
}
