package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.PendingBet;
import project.persistence.entities.Bet;
import project.persistence.entities.User;
import project.persistence.repositories.BetRepository;
import project.persistence.repositories.PendingBetRepository;
import project.service.BetService;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.ZonedDateTime;

@Service
public class BetServiceImplementation implements BetService {

    private PendingBetRepository pendingBetRepository;
    private BetRepository betRepository;

    @Autowired
    public BetServiceImplementation(PendingBetRepository pendingBetRepository, BetRepository betRepository){
        this.pendingBetRepository = pendingBetRepository;
        this.betRepository = betRepository;
    }

    /*
    Pending bet service implementation
     */
    @Override
    public void savePendingBet(PendingBet pendingBet){
         pendingBetRepository.save(pendingBet);
    }

    @Override
    public void savePendingBet(PendingBet pendingBet, User sender, User receiver){

        //TODO laga
        pendingBet.setDateAndTimeCreated(ZonedDateTime.now().toString());

        //TODO LAGA floating point number
        double odds = pendingBet.getOddsSender();
        double likur = Math.floor((1 / odds) * 100 * 100) / 100;
        double oppOdds = Math.floor((1 / (100.0 - likur)) * 100 * 100) / 100;
        double oppAmount = (((pendingBet.getAmountSender() * odds) / oppOdds) * 100) /100;
        System.out.println("opp odds " + oppOdds + " opp amount " + oppAmount);
        System.out.println("your odds" + odds + " your amount " + pendingBet.getAmountSender());
        pendingBet.setOddsReceiver(oppOdds);
        pendingBet.setAmountReceiver(oppAmount);

        pendingBet.setAcceptSender(true);
        pendingBet.setAcceptReceiver(false);

        //add to senders pending bets
        Set<PendingBet> senderPendingBets = sender.getPendingBets();
        if(senderPendingBets == null){
            senderPendingBets = new HashSet<>();
        }
        senderPendingBets.add(pendingBet);
        pendingBet.setSender(sender.getUsername());
        pendingBet.setSenderID(sender.getId());

        //add to receiver pending bets
        Set<PendingBet> receiverPendingBets = receiver.getPendingBets();
        if(receiverPendingBets == null){
            receiverPendingBets = new HashSet<>();
        }
        receiverPendingBets.add(pendingBet);

        pendingBet.setReceiverID(receiver.getId());
        pendingBet.setReceiver(receiver.getUsername());

        pendingBetRepository.save(pendingBet);
    }

    @Override
    public void deletePendingBet(PendingBet pendingBet){
        pendingBetRepository.delete(pendingBet);
    }


    @Override
    public List<PendingBet> findPendingBetBySender(String sender){
        return pendingBetRepository.findBySender(sender);
    }



    @Override
    public List<PendingBet> findPendingBetByReceiver(String receiver){
        return pendingBetRepository.findByReceiver(receiver);
    }

    @Override
    public PendingBet findById(long Id){
        return pendingBetRepository.findOne(Id);
    }

    /*
    Bet service implementation
     */

    @Override
    public void deleteBet(Bet bet){
        betRepository.delete(bet);
    }

    @Override
    public void saveBet(Bet bet){
        betRepository.save(bet);
    }

    @Override
    public List<Bet> findBetBySender(String sender){
        return betRepository.findBySender(sender);
    }

    @Override
    public List<Bet> findBetByReceiver(String receiver){
        return betRepository.findByReceiver(receiver);
    }

    @Override
    public List<Bet> getActiveBets(){
        return betRepository.findActiveBets();
    }

    @Override
    public List<Bet> getResolvedBets(){
        return betRepository.findResolvedBets();
    }
}
