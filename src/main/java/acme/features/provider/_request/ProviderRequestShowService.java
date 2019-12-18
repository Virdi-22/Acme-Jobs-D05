
package acme.features.provider._request;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities._requests._Request;
import acme.entities.roles.Provider;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractShowService;

@Service
public class ProviderRequestShowService implements AbstractShowService<Provider, _Request> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	private ProviderRequestRepository repository;


	// AbstractShowService<Provider, _Request> interface -----------------------------------

	@Override
	public boolean authorise(final Request<_Request> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<_Request> request, final _Request entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title", "text", "reward", "ticker", "deadline");
	}

	@Override
	public _Request findOne(final Request<_Request> request) {
		assert request != null;

		_Request result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}
}
