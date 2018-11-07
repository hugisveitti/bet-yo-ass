package project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import project.persistence.entities.PendingBet;
import project.persistence.entities.Bet;
import project.persistence.entities.User;
import project.service.BetService;
import project.service.CustomUserDetailsService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * controller for the get and post requests for anything to do with bets and pending bets.
 * Post methods usually redirect to the userpage.
 * Try to only use controller to call methods from services.
 */

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
        //DONE - todo ekki senda current user - theta er hondlad a sidunnri sjalfri.
        model.addAttribute("users", users);
        return "SendBet";
    }



    @RequestMapping(value = "/sendbet", method = RequestMethod.POST)
    public String sendbetPost(@ModelAttribute("pendingBet") PendingBet pendingBet, Authentication authentication, Model model){

        User receiver = customUserDetailsService.findByUsername(pendingBet.getReceiver());
        User sender = customUserDetailsService.findByUsername(authentication.getName());
        try{
            betService.savePendingBet(pendingBet, sender, receiver);
            return "redirect:userpage";
        } catch (Exception e){
            System.out.println(e.getMessage());
            model.addAttribute("errorMsg", e.getMessage());
            return "SendBet";
        }


    }


    @RequestMapping(value = "/accept-pending-bet", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView acceptPendingBet(HttpServletRequest request, Authentication authentication, Model model){
        String pendingBetId = request.getParameter("pendingBetId");

        PendingBet acceptPendingBet = betService.findPendingBetById(Long.parseLong(pendingBetId));
        User currUser = customUserDetailsService.findByUsername(authentication.getName());

        try{
            betService.saveBet(acceptPendingBet, currUser);
        } catch (Exception e){
            model.addAttribute("errorMsg", e.getMessage());
        }
        return new RedirectView("userpage");
    }


    @RequestMapping(value = "/decline-pending-bet", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView declinePendingBet(HttpServletRequest request, Authentication authentication, Model model){
        String pendingBetId = request.getParameter("pendingBetId");
        PendingBet declinePendingBet = betService.findPendingBetById(Long.parseLong(pendingBetId));

        //tharf kannski ad sja til thess ad sa sem er ekki buinn ad accept-a er sa sem er loggadur inn
        //annars gaeti hinn userinn einhvern veginn sent inn decline bet

        User currUser = customUserDetailsService.findByUsername(authentication.getName());
        betService.deletePendingBet(declinePendingBet, currUser);

        return new RedirectView("userpage");
    }

    @RequestMapping(value = "/counter-pending-bet", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView counterPendingBet(HttpServletRequest request, Authentication authentication, Model model){
        String pendingBetId = request.getParameter("pendingBetId");

        PendingBet counterPendingBet = betService.findPendingBetById(Long.parseLong(pendingBetId));

        User currUser = customUserDetailsService.findByUsername(authentication.getName());
        double counterAmount = Double.parseDouble(request.getParameter("counterAmount"));
        double counterOdds = Double.parseDouble(request.getParameter("counterOdds"));
        betService.counterPendingBet(counterPendingBet, currUser, counterAmount, counterOdds);

        return new RedirectView("userpage");
    }

    @RequestMapping(value = "/vote-bet", method = RequestMethod.POST)
    @ResponseBody
    public RedirectView voteBet(HttpServletRequest request, Authentication authentication){
        String betId = request.getParameter("betId");
        String vote = request.getParameter("whoWon");
        Bet voteBet = betService.findBetById(Long.parseLong(betId));
        User currUser = customUserDetailsService.findByUsername(authentication.getName());

        betService.voteBet(voteBet, currUser, vote);

        return new RedirectView("userpage");
    }

}
