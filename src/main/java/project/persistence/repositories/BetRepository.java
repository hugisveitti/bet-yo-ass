package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Bet;
import project.persistence.entities.PendingBet;

import java.util.List;

/**
 * By extending the {@link JpaRepository} we have access to powerful methods.
 * For detailed information, see:
 * http://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/CrudRepository.html
 * http://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html
 *
 */
public interface BetRepository extends JpaRepository<Bet, Long> {

    Bet save(Bet bet);

    void delete(Bet bet);

    List<Bet> findAll();

    // If we need a custom query that maybe doesn't fit the naming convention used by the JPA repository,
    // then we can write it quite easily with the @Query notation, like you see below.
    // This method returns all Users where the length of the name is equal or greater than 3 characters.
    @Query(value = "SELECT b FROM Bet b where length(b.title) >= 3 ")
    List<Bet> findAllWithNameLongerThan3Chars();


    @Query(value = "SELECT b FROM Bet b WHERE b.id = ?1")
    Bet findOne(Long id);


    @Query(value = "SELECT b FROM Bet b WHERE b.hasBeenResolved = true")
    List<Bet> findResolvedBets();

    @Query(value = "SELECT b FROM Bet b WHERE b.hasBeenResolved = false")
    List<Bet> findActiveBets();


    List<Bet> findBySender(String sender);
    List<Bet> findByReceiver(String receiver);

}
