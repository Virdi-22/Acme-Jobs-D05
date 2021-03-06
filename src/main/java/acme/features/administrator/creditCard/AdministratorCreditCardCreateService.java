
package acme.features.administrator.creditCard;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.creditCard.CreditCard;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorCreditCardCreateService implements AbstractCreateService<Administrator, CreditCard> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	AdministratorCreditCardRepository repository;


	// AbstractCreateService<Administrator, CreditCard> interface --------------------------

	@Override
	public boolean authorise(final Request<CreditCard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<CreditCard> request, final CreditCard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creditCardNumber", "expirationDate", "brand", "holder");

	}

	@Override
	public CreditCard instantiate(final Request<CreditCard> request) {
		CreditCard result;

		result = new CreditCard();

		return result;
	}

	@Override
	public void validate(final Request<CreditCard> request, final CreditCard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;
		if (!errors.hasErrors("expirationDate")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			String[] fecha = entity.getExpirationDate().split("/");
			String date = fecha[0].trim() + "/" + fecha[1].trim() + "/01 00:00";
			Date deadline = new Date(date);
			boolean isInFuture = deadline.after(minimumDeadline);
			errors.state(request, isInFuture, "expirationDate", "administrator.creditCard.error.inFuture");
		}

	}

	@Override
	public void create(final Request<CreditCard> request, final CreditCard entity) {

		this.repository.save(entity);

	}

}
