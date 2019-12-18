
package acme.features.sponsor.creditCard;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;

@Service
public class SponsorCreditCardListService implements AbstractListService<Sponsor, CreditCard> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	SponsorCreditCardRepository repository;


	// AbstractListService<Sponsor, CreditCard> interface ----------------------------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creditCardNumber", "expirationDate");

	}

	@Override
	public Collection<CreditCard> findMany(final Request<CreditCard> request) {
		assert request != null;

		Collection<CreditCard> result;
		Principal principal = request.getPrincipal();
		result = this.repository.findManyBySponsorId(principal.getActiveRoleId());

		return result;
	}

}
