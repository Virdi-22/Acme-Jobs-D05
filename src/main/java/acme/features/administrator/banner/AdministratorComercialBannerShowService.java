
package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.ComercialBanner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorComercialBannerShowService implements AbstractShowService<Administrator, ComercialBanner> {

	// Internal state ---------------------------------------------------------------

	@Autowired
	private AdministratorComercialBannerRepository repository;


	// AbstractShowService<Administrator, ComercialBanner> interface ----------------

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

		request.unbind(entity, model, "target", "slogan");
		model.setAttribute("creditCardId", entity.getCreditCard().getId());
		model.setAttribute("creditCardNumber", entity.getCreditCard().getCreditCardNumber());
		model.setAttribute("expirationDate", entity.getCreditCard().getExpirationDate());

	}

	@Override
	public ComercialBanner findOne(final Request<ComercialBanner> request) {
		assert request != null;

		ComercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
