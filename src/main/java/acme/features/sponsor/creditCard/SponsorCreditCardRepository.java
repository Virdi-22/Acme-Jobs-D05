
package acme.features.sponsor.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCard.CreditCard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCreditCardRepository extends AbstractRepository {

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findOneById(int id);

	@Query("select c from CreditCard c inner join Sponsor s on s.id=?1 and s.creditCard.id=c.id")
	Collection<CreditCard> findManyBySponsorId(int sponsorId);
}
