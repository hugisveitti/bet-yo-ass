package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;
import project.persistence.entities.Bet;
import project.persistence.entities.User;

@Controller
public class BetController {


    @RequestMapping(value = "/sendbet", method = RequestMethod.POST)
    public RedirectView sendbetPost(@ModelAttribute("bet") Bet bet, Model model){


        System.out.println("############");
        System.out.println(model);
        System.out.println(bet);


        System.out.println(23);
        return new RedirectView("/sendbet");
    }



}
