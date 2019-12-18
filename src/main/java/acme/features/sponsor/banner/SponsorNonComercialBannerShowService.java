
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonComercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorNonComercialBannerShowService implements AbstractShowService<Sponsor, NonComercialBanner> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	SponsorNonComercialBannerRepository repository;


	// AbstractShowService<Sponsor, NonComercialBanner> interface --------------------------

	@Override
	public boolean authorise(final Request<NonComercialBanner> request) {
		assert request != null;

		boolean result;
		int nonComercialBannerId;
		NonComercialBanner nonComercialBanner;
		Sponsor sponsor;
		Principal principal;

		nonComercialBannerId = request.getModel().getInteger("id");
		nonComercialBanner = this.repository.findOneById(nonComercialBannerId);
		sponsor = nonComercialBanner.getSponsor();
		principal = request.getPrincipal();
		result = sponsor.getUserAccount().getId() == principal.getAccountId();

		return result;
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
		NonComercialBanner result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
