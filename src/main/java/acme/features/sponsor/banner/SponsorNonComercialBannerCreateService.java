
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
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorNonComercialBannerCreateService implements AbstractCreateService<Sponsor, NonComercialBanner> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	SponsorNonComercialBannerRepository repository;


	// AbstractCreateService<Sponsor, NonComercialBanner> interface ------------------------

	@Override
	public boolean authorise(final Request<NonComercialBanner> request) {
		assert request != null;

		return true;
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
	public NonComercialBanner instantiate(final Request<NonComercialBanner> request) {
		NonComercialBanner result;

		result = new NonComercialBanner();

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
	public void create(final Request<NonComercialBanner> request, final NonComercialBanner entity) {
		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.finOneSponsorById(principal.getActiveRoleId());
		entity.setSponsor(sponsor);
		this.repository.save(entity);

	}

}
