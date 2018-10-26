package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import project.persistence.entities.PendingBet;
import project.persistence.entities.User;
import project.service.BetService;
import project.service.CustomUserDetailsService;
import sun.misc.Request;

import javax.servlet.http.HttpServletRequest;
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

        //calc opponent odds
        double odds = pendingBet.getOddsSender();
        double likur = Math.floor((1 / odds) * 100 * 100) / 100;
        double oppOdds = Math.floor((1 / (100.0 - likur)) * 100 * 100) / 100;
        double oppAmount = (((pendingBet.getAmountSender() * odds) / oppOdds) * 100) /100;
        System.out.println("opp odds " + oppOdds + " opp amount " + oppAmount);
        System.out.println("your odds" + odds + " your amount " + pendingBet.getAmountSender());
        newPendingBet.setOddsReceiver(oppOdds);
        newPendingBet.setAmountReceiver(oppAmount);


        newPendingBet.setAcceptSender(true);
        newPendingBet.setAcceptReceiver(false);

        newPendingBet.setDateAndTimeResolve(pendingBet.getDateAndTimeResolve());
        System.out.println("resolve " + pendingBet.getDateAndTimeResolve());
        System.out.println("created " + newPendingBet.getDateAndTimeCreated());

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

        System.out.println(pendingBet);
        return "redirect:userpage";
    }


    @RequestMapping(value = "/accept-pending-bet", method = RequestMethod.POST)
    @ResponseBody
    public String acceptPendingBet(HttpServletRequest request){
        String pendingBetId = request.getParameter("pendingBetId");
        String who = request.getParameter("who");
        System.out.println(pendingBetId);
        System.out.println(who);
        //TODO senda í bet reposirtory og eyða úr pendingbet
        return "redirect:userpage";
    }

}
