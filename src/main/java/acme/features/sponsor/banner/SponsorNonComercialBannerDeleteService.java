
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonComercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class SponsorNonComercialBannerDeleteService implements AbstractDeleteService<Sponsor, NonComercialBanner> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	SponsorNonComercialBannerRepository repository;


	// AbstractDeleteService<Sponsor, NonComercialBanner> interface ------------------------

	@Override
	public boolean authorise(final Request<NonComercialBanner> request) {
		assert request != null;
		boolean res;

		int nonComercialBannerId = request.getModel().getInteger("id");
		NonComercialBanner nonComercialBanner = this.repository.findOneById(nonComercialBannerId); //Banner sobre la que se realiza la opercion

		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.finOneSponsorById(principal.getActiveRoleId()); //Sponsor que realiza la operacion

		if (nonComercialBanner.getSponsor().getId() != sponsor.getId()) {
			res = false;
		} else {
			res = true;
		}

		return res;
	}

	@Override
	public void bind(final Request<NonComercialBanner> request, final NonComercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<NonComercialBanner> request, final NonComercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "target", "slogan", "jingle");

	}

	@Override
	public NonComercialBanner findOne(final Request<NonComercialBanner> request) {
		assert request != null;

		NonComercialBanner result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<NonComercialBanner> request, final NonComercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<NonComercialBanner> request, final NonComercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
