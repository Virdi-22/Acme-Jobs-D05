
package acme.features.authenticated._request;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities._requests._Request;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedRequestListService implements AbstractListService<Authenticated, _Request> {

	// Internal state ---------------------

	@Autowired
	AuthenticatedRequestRepository repository;


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

		result = this.repository.findActiveAll();

		return result;
	}

}
