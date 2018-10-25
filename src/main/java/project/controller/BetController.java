package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import project.persistence.entities.PendingBet;
import project.persistence.entities.User;
import project.service.BetService;
import project.service.CustomUserDetailsService;

import java.util.List;


@Controller
public class BetController {

    private BetService pendingBetService;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public BetController(BetService pendingBetService, CustomUserDetailsService customUserDetailsService){
        this.pendingBetService = pendingBetService;
        this.customUserDetailsService = customUserDetailsService;
    }


    @RequestMapping(value="/sendbet", method = RequestMethod.GET)
    public String sendBetViewGet(@ModelAttribute("pendingbet") PendingBet pendingBet, Model model){
        List<User> users = customUserDetailsService.findAll();
        model.addAttribute("pendingBet",new PendingBet());
        model.addAttribute("users", users);
        return "SendBet";
    }



    @RequestMapping(value = "/sendbet", method = RequestMethod.POST)
    public String sendbetPost(@ModelAttribute("pendingBet") PendingBet pendingBet, Model model){


        System.out.println("############");
        System.out.println(model);
        System.out.println(pendingBet);


        System.out.println(23);
        return "UserPage";
    }

}
