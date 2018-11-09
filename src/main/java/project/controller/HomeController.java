package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import project.persistence.entities.User;
import org.springframework.ui.Model;
import project.service.CustomUserDetailsService;

/**
 * Controller for the "/" and some get requests that are less related to users and changing and saving bets.
 */

@Controller
public class HomeController {

    CustomUserDetailsService customUserDetailsService;

    // Dependency Injection
    @Autowired
    public HomeController(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    // Request mapping is the path that you want to map this method to
    // In this case, the mapping is the root "/", so when the project
    // is running and you enter "localhost:8080" into a browser, this
    // method is called
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(Model model, Authentication authentication){
        if(authentication != null) {
            User currUser = customUserDetailsService.findByUsername(authentication.getName());
            model.addAttribute("user", currUser);
        }
        // The string "Index" that is returned here is the name of the view
        // (the Index.jsp file) that is in the path /main/webapp/WEB-INF/jsp/
        // If you change "Index" to something else, be sure you have a .jsp
        // file that has the same name
        return "Index";
    }



    //TODO implement 403 and 404 pages
    @RequestMapping(value="/403", method = RequestMethod.GET)
    public String errorViewGet(){
        System.out.println("ERROR");
        return "403";
    }
}
