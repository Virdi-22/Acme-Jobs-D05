
package acme.features.provider._request;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities._requests._Request;
import acme.entities.roles.Provider;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractCreateService;

@Service
public class ProviderRequestCreateService implements AbstractCreateService<Provider, _Request> {

	// Internal state --------------------------------------------------------

	@Autowired
	ProviderRequestRepository repository;


	// AbstractUpdateService<Provider, _Request> interface -----------------

	@Override
	public boolean authorise(final Request<_Request> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<_Request> request, final _Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<_Request> request, final _Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "text", "moment", "reward", "ticker", "deadline");

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("check", "false");
		} else {
			request.transfer(model, "check");
		}

	}

	@Override
	public _Request instantiate(final Request<_Request> request) {
		_Request result;

		result = new _Request();

		return result;
	}

	@Override
	public void validate(final Request<_Request> request, final _Request entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Variables

		Calendar calendar;
		Date minimumDeadline;
		Money reward;
		boolean isEUR, isAccepted;
		_Request existing;
		reward = entity.getReward();

		// Validating the deadline

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			boolean isInFuture = entity.getDeadline().after(minimumDeadline);
			errors.state(request, isInFuture, "deadline", "provider.request.error.inFuture");
		}

		// Validating the ticker

		if (!errors.hasErrors("ticker")) {
			existing = this.repository.findOneByTicker(entity.getTicker());
			errors.state(request, existing == null, "ticker", "provider.request.error.existing");
		}

		// Validating the currency
		if (!errors.hasErrors("reward")) {
			isEUR = reward.getCurrency().equals("EUR") || reward.getCurrency().equals("€");
			errors.state(request, isEUR, "reward", "provider.request.error.EUR");
		}

		// Validating the checkbox

		isAccepted = request.getModel().getBoolean("check");
		errors.state(request, isAccepted, "check", "provider.request.error.must-accept");

	}

	@Override
	public void create(final Request<_Request> request, final _Request entity) {
		assert request != null;
		assert entity != null;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);

	}

}
