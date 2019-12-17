
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.CheckSpam;
import acme.entities.banners.NonComercialBanner;
import acme.entities.configurations.Configuration;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorNonComercialBannerUpdateService implements AbstractUpdateService<Sponsor, NonComercialBanner> {

	@Autowired
	SponsorNonComercialBannerRepository repository;


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
		boolean isSpam;
		if (!errors.hasErrors()) {
			Configuration configuration = this.repository.findConfiguration();
			String text = entity.getJingle() + "," + entity.getSlogan() + "," + entity.getTarget();
			isSpam = CheckSpam.checkSpam(configuration, text);
			errors.state(request, !isSpam, "*", "sponsor.nonComercialBanner.error.spam");
		}

	}

	@Override
	public void update(final Request<NonComercialBanner> request, final NonComercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
