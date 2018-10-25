package project.service.Implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.persistence.entities.PendingBet;
import project.persistence.entities.Bet;
import project.persistence.repositories.BetRepository;
import project.persistence.repositories.PendingBetRepository;
import project.service.BetService;

import java.util.List;

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
    public Bet findBetByTitle(String title){
        return betRepository.findByTitle(title);
    }

}
