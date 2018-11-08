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
     */
    //eyda thessum kannski
    void savePendingBet(PendingBet pendingBet);

    /**
     * Save a {@link PendingBet}
     * @param pendingBet {@link PendingBet} to be saved
     * @param sender {@link User} who initially sent the bet
     * @param receiver {@link User} who initially received the bet
     */
    void savePendingBet(PendingBet pendingBet, User sender, User receiver) throws Exception;

    /**
     * Delete {@link PendingBet}
     * @param pendingBet {@link PendingBet} to be deleted
     */
    void deletePendingBet(PendingBet pendingBet, User currUser);


    /**
     * Get one (@link PendingBet) from id
     * @param id (@link Long) to be found
     * @return one (@link PendingBet)
     */
    PendingBet findPendingBetById(Long id);


    /**
     * Counter a {@link PendingBet}
     * @param counterPendingBet the {@link PendingBet} being countered
     * @param currUser the {@link User} countering the bet
     * @param counterAmount {@link Double} amount being countered
     * @param counterOdds {@link Double} odds being countered
     */
    void counterPendingBet(PendingBet counterPendingBet, User currUser, double counterAmount, double counterOdds) throws Exception;


    /**
     * Delete {@link Bet}
     * @param bet {@link Bet} to be deleted
     */
    void deleteBet(Bet bet);

    //delete
    void saveBet(Bet bet);

    /**
     * Save a {@link Bet}
     * @param pendingBet {@link PendingBet} to be saved as a new Bet
     * @param currUser {@link User} who is accepting the bet
     */
    void saveBet(PendingBet pendingBet, User currUser) throws Exception;

    /**
     * Get one (@link Bet) from id
     * @param id (@link Long) to be found
     * @return one (@link PendingBet)
     */
    Bet findBetById(Long id);

    /**
     * Vote how won a {@link Bet}
     * @param voteBet {@link Bet} which is the bet being voted on
     * @param currUse {@link User} who is voting
     * @param vote {@link String} what currUser voted
     */
    void voteBet(Bet voteBet, User currUser, String vote);
}
