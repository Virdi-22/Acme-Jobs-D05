
package acme.features.sponsor.banner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.CheckSpam;
import acme.entities.banners.ComercialBanner;
import acme.entities.configurations.Configuration;
import acme.entities.roles.Sponsor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class SponsorComercialBannerUpdateService implements AbstractUpdateService<Sponsor, ComercialBanner> {

	@Autowired
	SponsorComercialBannerRepository repository;


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

		request.unbind(entity, model, "target", "slogan", "holder", "brand");

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
		boolean isSpam;
		if (!errors.hasErrors()) {
			Configuration configuration = this.repository.findConfiguration();
			String text = entity.getBrand() + "," + entity.getHolder() + "," + entity.getSlogan() + "," + entity.getTarget();
			isSpam = CheckSpam.checkSpam(configuration, text);
			errors.state(request, !isSpam, "*", "sponsor.nonComercialBanner.error.spam");
		}

	}

	@Override
	public void update(final Request<ComercialBanner> request, final ComercialBanner entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
