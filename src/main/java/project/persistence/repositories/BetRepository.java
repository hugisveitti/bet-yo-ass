package project.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.persistence.entities.Bet;


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

    @Query(value = "SELECT b FROM Bet b WHERE b.id = ?1")
    Bet findOne(Long id);

/*
Hugsa ad vid thurfum aldrei ad nota thetta thvi user geymir hver sin active og recsolved bet eru...
    @Query(value = "SELECT b FROM Bet b WHERE b.hasBeenResolved = true")
    List<Bet> findResolvedBets();

    @Query(value = "SELECT b FROM Bet b WHERE b.hasBeenResolved = false")
    List<Bet> findActiveBets();
*/

}
