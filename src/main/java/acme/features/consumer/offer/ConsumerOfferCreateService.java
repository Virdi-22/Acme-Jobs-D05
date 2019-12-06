
package acme.features.consumer.offer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.offers.Offer;
import acme.entities.roles.Consumer;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class ConsumerOfferCreateService implements AbstractCreateService<Consumer, Offer> {

	// Internal state --------------------------------------------------------

	@Autowired
	ConsumerOfferRepository repository;


	// AbstractUpdateService<Authenticated, Offer> interface -----------------

	@Override
	public boolean authorise(final Request<Offer> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Offer> request, final Offer entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment");

	}

	@Override
	public void unbind(final Request<Offer> request, final Offer entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "minMoney", "maxMoney", "ticker");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("check", "false");
		} else {
			request.transfer(model, "check");
		}

	}

	@Override
	public Offer instantiate(final Request<Offer> request) {
		// TODO Auto-generated method stub
		Offer result;

		result = new Offer();

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result.setCreationMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<Offer> request, final Offer entity, final Errors errors) {
		// TODO Auto-generated method stub
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Variables

		Calendar calendar;
		Date minimumDeadline;
		Money minMoney, maxMoney;
		boolean isEURMin, isEURMax, isMaxMoreThanMin, isAccepted;
		Offer existing;

		minMoney = entity.getMinMoney();
		maxMoney = entity.getMaxMoney();

		// Validating the deadline

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			boolean isInFuture = entity.getDeadline().after(minimumDeadline);
			errors.state(request, isInFuture, "deadline", "consumer.offer.error.inFuture");
		}

		// Validating the ticker

		if (!errors.hasErrors("ticker")) {
			existing = this.repository.findOneByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "consumer.offer.error.existing");
		}

		// Validating the currencies of minMoney and maxMoney and maxMoney > minMoney

		if (!errors.hasErrors("minMoney") || !errors.hasErrors("maxMoney")) {
			isEURMin = minMoney.getCurrency().equals("EUR") || minMoney.getCurrency().equals("€");
			errors.state(request, isEURMin, "minMoney", "consumer.offer.error.EURminMoney");

			isEURMax = maxMoney.getCurrency().equals("EUR") || maxMoney.getCurrency().equals("€");
			errors.state(request, isEURMax, "maxMoney", "consumer.offer.error.EURmaxMoney");

			isMaxMoreThanMin = maxMoney.getAmount() > minMoney.getAmount();
			errors.state(request, isMaxMoreThanMin, "maxMoney", "consumer.offer.error.maxMoreThanMin");
		}

		// Validating the checkbox

		isAccepted = request.getModel().getBoolean("check");
		errors.state(request, isAccepted, "check", "consumer.offer.error.must-accept");
	}

	@Override
	public void create(final Request<Offer> request, final Offer entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);
		this.repository.save(entity);

	}

}
