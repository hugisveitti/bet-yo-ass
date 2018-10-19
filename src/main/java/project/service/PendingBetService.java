package project.service;

import project.persistence.entities.PendingBet;

import java.util.List;

public interface PendingBetService {


    /**
     * Save a {@link PendingBet}
     * @param pendingBet {@link PendingBet} to be saved
     * @return {@link PendingBet} that was saved
     */
    PendingBet save(PendingBet pendingBet);



    /**
     * Delete {@link PendingBet}
     * @param pendingBet {@link PendingBet} to be deleted
     */
    void delete(PendingBet pendingBet);

    /**
     * Get all {@link PendingBet}s from a sender
     * @return A list of {@link PendingBet}s
     */
    List<PendingBet> findBySender(String sender);


    /**
     * Get all {@link PendingBet}s from a receiver
     * @return A list of {@link PendingBet}s
     */
    List<PendingBet> findByReceiver(String receiver);
}
