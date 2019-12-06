
package acme.features.provider._request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities._requests._Request;
import acme.entities.roles.Provider;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class ProviderRequestListService implements AbstractListService<Provider, _Request> {

	// Internal state ---------------------

	@Autowired
	ProviderRequestRepository repository;


	// AbstractListService<Authenticated, JobRequest> interface ----------------

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

		request.unbind(entity, model, "title", "ticker", "deadline");
	}

	@Override
	public Collection<_Request> findMany(final Request<_Request> request) {
		assert request != null;

		Collection<_Request> result;

		result = this.repository.findActiveRequests();

		return result;
	}
}
