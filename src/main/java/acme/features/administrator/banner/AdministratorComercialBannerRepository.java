
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.banners.ComercialBanner;
import acme.entities.creditCard.CreditCard;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorComercialBannerRepository extends AbstractRepository {

	@Query("select a from ComercialBanner a where a.id = ?1")
	ComercialBanner findOneById(int id);

	@Query("select a from ComercialBanner a")
	Collection<ComercialBanner> findManyAll();

	@Query("select b from ComercialBanner b where b.creditCard.id = ?1")
	Collection<ComercialBanner> findManyByCredtCardId(int creditCardId);

	@Query("select c from CreditCard c where c.id = ?1")
	CreditCard findCreditCardById(int id);
}
