
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.ComercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorComercialBannerDeleteService implements AbstractDeleteService<Sponsor, ComercialBanner> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	SponsorComercialBannerRepository repository;


	// AbstractDeleteService<Sponsor, ComercialBanner> interface --------------------------

	@Override
	public boolean authorise(final Request<ComercialBanner> request) {
		assert request != null;
		boolean res;

		int comercialBannerId = request.getModel().getInteger("id");
		ComercialBanner comercialBanner = this.repository.findOneById(comercialBannerId); //Banner sobre la que se realiza la opercion

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.finOneSponsorById(principal.getActiveRoleId()); //Sponsor que realiza la operacion

		if (comercialBanner.getSponsor().getId() != sponsor.getId()) {
			res = false;
		} else {
			res = true;
		}

		return res;
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
