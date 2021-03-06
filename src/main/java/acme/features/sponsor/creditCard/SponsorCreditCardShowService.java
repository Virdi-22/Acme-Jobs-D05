
package acme.features.sponsor.creditCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.entities.roles.Sponsor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class SponsorCreditCardShowService implements AbstractShowService<Sponsor, CreditCard> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	SponsorCreditCardRepository repository;


	// AbstractShowService<Sponsor, CreditCard> interface ----------------------------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		boolean res;

		Principal principal = request.getPrincipal();
		int sponsorId = principal.getActiveRoleId(); //The id of the sponsor making the change to the credit card

		CreditCard creditCard = this.repository.findOneById(request.getModel().getInteger("id"));
		int sponsorCreditCardId = creditCard.getSponsor().getId(); //The id of the sponsor who own the credit card

		if (sponsorId != sponsorCreditCardId) { //Check if they are the same
			res = false;
		} else {
			res = true;
		}

		return res;
	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creditCardNumber", "expirationDate", "brand", "holder");

	}

	@Override
	public CreditCard findOne(final Request<CreditCard> request) {
		assert request != null;

		CreditCard result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
