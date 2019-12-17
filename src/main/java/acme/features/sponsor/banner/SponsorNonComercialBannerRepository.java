
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonComercialBanner;
import acme.entities.configurations.Configuration;
import acme.entities.roles.Sponsor;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonComercialBannerRepository extends AbstractRepository {

	@Query("select b from NonComercialBanner b where b.id = ?1")
	NonComercialBanner findOneById(int id);

	@Query("select b from NonComercialBanner b where b.sponsor.id = ?1")
	Collection<NonComercialBanner> findManyBySponsorId(int sponsorId);

	@Query("select s from Sponsor s where s.id=?1")
	Sponsor finOneSponsorById(int sponsorid);

	@Query("select c from Configuration c")
	Configuration findConfiguration();
}
