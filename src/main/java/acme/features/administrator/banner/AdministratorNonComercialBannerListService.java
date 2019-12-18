
package acme.features.administrator.banner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.NonComercialBanner;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractListService;

@Service
public class AdministratorNonComercialBannerListService implements AbstractListService<Administrator, NonComercialBanner> {

	// Internal state ------------------------------------------------------------------------

	@Autowired
	AdministratorNonComercialBannerRepository repository;


	// AbstractListService<Administrator,NonComercialBanner> interface -----------------------

	@Override
	public boolean authorise(final Request<NonComercialBanner> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<NonComercialBanner> request, final NonComercialBanner entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "target", "slogan", "jingle");

	}

	@Override
	public Collection<NonComercialBanner> findMany(final Request<NonComercialBanner> request) {
		assert request != null;

		Collection<NonComercialBanner> result;

		result = this.repository.findManyAll();

		return result;
	}

}
