
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonComercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorNonComercialBannerRepository extends AbstractRepository {

	@Query("select b from NonComercialBanner b where b.id = ?1")
	NonComercialBanner findOneById(int id);

	@Query("select b from NonComercialBanner b where b.sponsor.id = ?1")
	Collection<NonComercialBanner> findManyBySponsorId(int sponsorId);
}
