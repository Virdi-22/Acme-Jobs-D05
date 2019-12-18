
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

	@Autowired
	SponsorCreditCardRepository repository;


	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		boolean res;

		Principal principal = request.getPrincipal();
		int sponsorId = principal.getActiveRoleId(); //El id del sponsor que realiza la operacion sobre la trjeta

		CreditCard creditCard = this.repository.findOneById(request.getModel().getInteger("id"));
		int sponsorCreditCardId = creditCard.getSponsor().getId(); //El id del sponsor al que pertenece la trajeta

		if (sponsorId != sponsorCreditCardId) { //Comprueba si los ids son iguales
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
