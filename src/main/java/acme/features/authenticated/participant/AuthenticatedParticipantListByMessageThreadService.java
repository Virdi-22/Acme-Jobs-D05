
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractListService;

@Service
public class AuthenticatedParticipantListByMessageThreadService implements AbstractListService<Authenticated, Participant> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	// AbstractListService<Authenticated, Participant> interface ---------------------------

	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "isOwner");
		model.setAttribute("messageThreadId", entity.getMessageThread().getId());
		model.setAttribute("messageThreadName", entity.getMessageThread().getTitle());
		model.setAttribute("authenticatedName", entity.getAuthenticated().getUserAccount().getUsername());
	}

	@Override
	public Collection<Participant> findMany(final Request<Participant> request) {
		assert request != null;

		Collection<Participant> result;

		Integer messageThreadId = request.getModel().getInteger("messageThreadId");
		result = this.repository.findManyByMessageThread(messageThreadId);

		return result;
	}

}
