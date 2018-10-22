package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import project.persistence.entities.PendingBet;
import project.service.PendingBetService;

@Controller
@SessionAttributes("username")
public class PendingBetController {

    private PendingBetService pendingBetService;

    @Autowired
    public PendingBetController(PendingBetService pendingBetService){
        this.pendingBetService = pendingBetService;
    }

    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value="/sendbet", method = RequestMethod.GET)
    public ModelAndView sendBetViewGet(){
        ModelAndView model = new ModelAndView();
        model.addObject("pendingBet",new PendingBet());
        model.setViewName("/SendBet");
        return model;
    }


/*
    @RequestMapping(value = "/sendbet", method = RequestMethod.POST)
    public RedirectView sendbetPost(@ModelAttribute("pendingBet") PendingBet bet, Model model){


        System.out.println("############");
        System.out.println(model);
        System.out.println(bet);


        System.out.println(23);
        return new RedirectView("/sendbet");
    }

*/

}
