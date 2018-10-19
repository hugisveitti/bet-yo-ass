package project.service.Implementation;

import project.persistence.entities.PendingBet;
import project.persistence.repositories.PendingBetRepository;
import project.service.PendingBetService;

import java.util.List;

public class PendingBetServiceImplementation implements PendingBetService {

    PendingBetRepository repository;

    public PendingBetServiceImplementation(PendingBetRepository repository){
        this.repository = repository;
    }

    @Override
    public PendingBet save(PendingBet pendingBet){
        return repository.save(pendingBet);
    }


    @Override
    public void delete(PendingBet pendingBet){
        repository.delete(pendingBet);
    }

    @Override
    public List<PendingBet> findBySender(String sender){
        return repository.findBySender(sender);
    }

    @Override
    public List<PendingBet> findByReceiver(String receiver){
        return repository.findByReceiver(receiver);
    }
}
