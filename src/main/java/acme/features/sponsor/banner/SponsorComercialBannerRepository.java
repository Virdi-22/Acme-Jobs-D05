
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.ComercialBanner;
import acme.entities.configurations.Configuration;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorComercialBannerRepository extends AbstractRepository {

	@Query("select b from ComercialBanner b where b.id = ?1")
	ComercialBanner findOneById(int id);

	@Query("select b from ComercialBanner b where b.sponsor.id = ?1")
	Collection<ComercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findCreditCardById(int id);

	@Query("select s from Sponsor s where s.id=?1")
	Sponsor finOneSponsorById(int sponsorid);
}
