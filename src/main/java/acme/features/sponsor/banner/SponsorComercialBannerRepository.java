
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.ComercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface SponsorComercialBannerRepository extends AbstractRepository {

	@Query("select b from ComercialBanner b where b.id = ?1")
	ComercialBanner findOneById(int id);

	@Query("select b from ComercialBanner b where b.sponsor.id = ?1")
	Collection<ComercialBanner> findManyBySponsorId(int sponsorId);
}
