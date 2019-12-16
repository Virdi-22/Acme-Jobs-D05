
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.ComercialBanner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorComercialBannerListByCreditCardService implements AbstractListService<Administrator, ComercialBanner> {

	@Autowired
	AdministratorComercialBannerRepository repository;


	@Override
	public boolean authorise(final Request<ComercialBanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<ComercialBanner> request, final ComercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "target", "slogan", "holder", "brand");

	}

	@Override
	public Collection<ComercialBanner> findMany(final Request<ComercialBanner> request) {
		assert request != null;

		Collection<ComercialBanner> result;
		int creditCardId;

		creditCardId = request.getModel().getInteger("creditCardId");
		result = this.repository.findManyByCredtCardId(creditCardId);

		return result;
	}

}
