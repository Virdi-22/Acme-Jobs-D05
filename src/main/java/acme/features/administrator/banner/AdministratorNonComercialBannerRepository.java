
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.NonComercialBanner;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorNonComercialBannerRepository extends AbstractRepository {

	@Query("select a from NonComercialBanner a where a.id = ?1")
	NonComercialBanner findOneById(int id);

	@Query("select a from NonComercialBanner a")
	Collection<NonComercialBanner> findManyAll();
}
