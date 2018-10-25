package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import project.persistence.entities.PendingBet;
import project.persistence.entities.User;
import project.service.BetService;
import project.service.CustomUserDetailsService;

import java.time.ZonedDateTime;
import java.util.HashSet;
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


        System.out.println("############");
        System.out.println(model);
        System.out.println(pendingBet);

        User receiver = customUserDetailsService.findByUsername(pendingBet.getReceiver());

        PendingBet newPendingBet = new PendingBet();
        newPendingBet.setReceiver(receiver.getUsername());
        newPendingBet.setReceiverID(receiver.getId());

        newPendingBet.setTitle(pendingBet.getTitle());
        newPendingBet.setDescription(pendingBet.getDescription());

        //TODO laga
        newPendingBet.setDateAndTimeCreated(ZonedDateTime.now().toString());

        User sender = customUserDetailsService.findByUsername(authentication.getName());
        System.out.println(sender);
        newPendingBet.setSender(sender.getUsername());
        newPendingBet.setSenderID(sender.getId());

        //TODO CALC RECEIVER AMOUNT AND ODDS WITH ONLY SENDER AMOUNT AND ODDS
        newPendingBet.setOddsSender(pendingBet.getOddsSender());
        newPendingBet.setOddsReceiver(pendingBet.getOddsReceiver());
        newPendingBet.setAmountReceiver(pendingBet.getAmountReceiver());
        newPendingBet.setAmountSender(pendingBet.getAmountSender());



        newPendingBet.setAcceptSender(true);
        newPendingBet.setAcceptReceiver(false);

        //add to senders pending bets
        Set<PendingBet> senderPendingBets = sender.getPendingBets();
        if(senderPendingBets == null){
            senderPendingBets = new HashSet<>();
        }
        senderPendingBets.add(newPendingBet);


        //add to receiver pending bets
        Set<PendingBet> receiverPendingBets = receiver.getPendingBets();
        if(receiverPendingBets == null){
            receiverPendingBets = new HashSet<>();
        }
        receiverPendingBets.add(newPendingBet);


        betService.savePendingBet(newPendingBet);

        return "redirect:userpage";
    }

}
