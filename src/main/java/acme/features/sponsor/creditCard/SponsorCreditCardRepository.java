
package acme.features.sponsor.creditCard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorCreditCardRepository extends AbstractRepository {

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findOneById(int id);

	//@Query("select c from Sponsor s join s.creditCard c where s.id = ?1") not used
	@Query("select c from CreditCard c where c.sponsor.id=?1")
	Collection<CreditCard> findManyBySponsorId(int sponsorId);

	@Query("Select s from Sponsor s where s.id=?1")
	Sponsor findOneSponsorById(int id);
}
