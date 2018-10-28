package project.service;

import project.persistence.entities.Bet;
import project.persistence.entities.User;
import project.persistence.entities.PendingBet;

import java.util.List;

/*
Service for both bet repository and pending bet repository
 */

public interface BetService {


    /**
     * Save a {@link PendingBet}
     * @param pendingBet {@link PendingBet} to be saved
     * @return {@link PendingBet} that was saved
     */
    //eyda thessum kannski
    void savePendingBet(PendingBet pendingBet);

    void savePendingBet(PendingBet pendingBet, User sender, User receiver);

    /**
     * Delete {@link PendingBet}
     * @param pendingBet {@link PendingBet} to be deleted
     */
    void deletePendingBet(PendingBet pendingBet);


    /**
     * Get one (@link PendingBet) from id
     * @param Id (@link PendingBet) to be found
     * @return one (@link PendingBet)
     */
    PendingBet findPendingBetById(Long Id);



    void deleteBet(Bet bet);

    void saveBet(Bet bet);

    void saveBet(PendingBet pendingBet, User sender, User receiver);

    Bet findBetById(Long Id);
}
