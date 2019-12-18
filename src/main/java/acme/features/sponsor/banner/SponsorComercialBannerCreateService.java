
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.CheckSpam;
import acme.entities.banners.ComercialBanner;
import acme.entities.configurations.Configuration;
import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class SponsorComercialBannerCreateService implements AbstractCreateService<Sponsor, ComercialBanner> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	SponsorComercialBannerRepository repository;


	// AbstractCreateService<Sppnsor, ComercialBanner> interface ---------------------------

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
	public ComercialBanner instantiate(final Request<ComercialBanner> request) {
		ComercialBanner result;
		int creditCardId;
		CreditCard creditCard;
		creditCardId = request.getModel().getInteger("creditCardId");
		creditCard = this.repository.findCreditCardById(creditCardId);
		result = new ComercialBanner();
		result.setCreditCard(creditCard);

		return result;
	}

	@Override
	public void validate(final Request<ComercialBanner> request, final ComercialBanner entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		boolean isSpam;
		if (!errors.hasErrors()) {
			Configuration configuration = this.repository.findConfiguration();
			String text = entity.getSlogan() + "," + entity.getTarget();
			isSpam = CheckSpam.checkSpam(configuration, text);
			errors.state(request, !isSpam, "*", "sponsor.nonComercialBanner.error.spam");
		}

	}

	@Override
	public void create(final Request<ComercialBanner> request, final ComercialBanner entity) {
		assert request != null;
		assert entity != null;
		Principal principal = request.getPrincipal();
		Sponsor sponsor = this.repository.finOneSponsorById(principal.getActiveRoleId());
		entity.setSponsor(sponsor);
		this.repository.save(entity);

	}

}
