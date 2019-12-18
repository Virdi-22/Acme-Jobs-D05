
package acme.features.administrator.banner;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.banners.ComercialBanner;
import acme.entities.creditCard.CreditCard;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorComercialBannerCreateService implements AbstractCreateService<Administrator, ComercialBanner> {

	@Autowired
	AdministratorComercialBannerRepository repository;


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
		Calendar calendar;
		Date minimumDeadline;
		if (!errors.hasErrors("expirationDate")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			String[] fecha = entity.getCreditCard().getExpirationDate().split("/");
			String date = fecha[0].trim() + "/" + fecha[1].trim() + "/01 00:00";
			Date deadline = new Date(date);
			boolean isInFuture = deadline.after(minimumDeadline);
			errors.state(request, isInFuture, "expirationDate", "administrator.comercialbanner.error.inFuture");
		}

	}

	@Override
	public void create(final Request<ComercialBanner> request, final ComercialBanner entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);
	}

}
