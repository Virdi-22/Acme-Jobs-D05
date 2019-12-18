
package acme.features.sponsor.banner;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.ComercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorComercialBannerListMineService implements AbstractListService<Sponsor, ComercialBanner> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	SponsorComercialBannerRepository repository;


	// AbstractListService<Sponsor, ComercialBanner> interface -----------------------------

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

	}

	@Override
	public Collection<ComercialBanner> findMany(final Request<ComercialBanner> request) {
		assert request != null;
		Collection<ComercialBanner> result;
		Principal principal = request.getPrincipal();
		result = this.repository.findManyBySponsorId(principal.getActiveRoleId());

		return result;
	}

}
