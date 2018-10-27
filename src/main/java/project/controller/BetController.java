package project.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.persistence.entities.PendingBet;
import project.persistence.entities.User;
import project.persistence.entities.Bet;
import project.service.BetService;
import project.service.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;


@Controller
public class BetController {

    private BetService betService;
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    public BetController(BetService betService, CustomUserDetailsService customUserDetailsService){
        this.betService = betService;
        this.customUserDetailsService = customUserDetailsService;
    }


    @RequestMapping(value="/sendbet", method = RequestMethod.GET)
    public String sendBetViewGet(@ModelAttribute("pendingbet") PendingBet pendingBet, Model model){
        List<User> users = customUserDetailsService.findAll();
        model.addAttribute("pendingBet",new PendingBet());
        //todo ekki senda current user
        model.addAttribute("users", users);
        return "SendBet";
    }



    @RequestMapping(value = "/sendbet", method = RequestMethod.POST)
    public String sendbetPost(@ModelAttribute("pendingBet") PendingBet pendingBet, Authentication authentication, Model model){

        User receiver = customUserDetailsService.findByUsername(pendingBet.getReceiver());
        User sender = customUserDetailsService.findByUsername(authentication.getName());

        betService.savePendingBet(pendingBet, sender, receiver);

        return "redirect:userpage";
    }


    @RequestMapping(value = "/accept-pending-bet", method = RequestMethod.POST)
    @ResponseBody
    public String acceptPendingBet(HttpServletRequest request, Authentication authentication){
        String pendingBetId = request.getParameter("pendingBetId");
        String who = request.getParameter("who");
        System.out.println(pendingBetId);
        System.out.println(who);
        //TODO senda í bet reposirtory og eyða úr pendingbet

        PendingBet acceptPendingBet = betService.findPendingBetById(Long.parseLong(pendingBetId));
        System.out.println(authentication.getName() + " " + acceptPendingBet.getSender() + " " + acceptPendingBet.isAcceptReceiver());
        System.out.println(authentication.getName() + " " + acceptPendingBet.getReceiver() + " " + acceptPendingBet.isAcceptSender());

        User currUser = customUserDetailsService.findByUsername(authentication.getName());

        if(currUser.getUsername().equals(acceptPendingBet.getReceiver())){
            //find other user to save bet to his bets
            User sender = customUserDetailsService.findByUsername(acceptPendingBet.getSender());
            betService.saveBet(acceptPendingBet, sender, currUser);

        } else if(currUser.getUsername().equals(acceptPendingBet.getSender())){
            //find other user to save bet to his bets
            User receiver = customUserDetailsService.findByUsername(acceptPendingBet.getReceiver());
            betService.saveBet(acceptPendingBet, currUser, receiver);

        } else {
            System.out.println("user is hacker");
        }

        //TODO laga virkar ekki
        return "redirect:userpage";
    }

}
