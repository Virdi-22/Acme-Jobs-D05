
package acme.features.administrator.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.ComercialBanner;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractDeleteService;

@Service
public class AdministratorComercialBannerDeleteService implements AbstractDeleteService<Administrator, ComercialBanner> {

	// Internal state ---------------------------------------------------------------

	@Autowired
	AdministratorComercialBannerRepository repository;


	// AbstractDeleteService<Administrator, ComercialBanner> interface --------------

	@Override
	public boolean authorise(final Request<ComercialBanner> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<ComercialBanner> request, final ComercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<ComercialBanner> request, final ComercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "target", "slogan");
		model.setAttribute("creditCardId", request.getModel().getInteger("creditCardId"));
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

	@Override
	public void validate(final Request<ComercialBanner> request, final ComercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<ComercialBanner> request, final ComercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
