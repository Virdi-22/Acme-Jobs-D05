
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuthenticatedParticipantDeleteService implements AbstractDeleteService<Authenticated, Participant> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	// AbstractDeleteService<Authenticated, Participant> interface -------------------------

	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;
		boolean result;
		int messageThreadId;
		Participant participant;
		Participant toDelete;
		Principal principal;

		toDelete = this.repository.findOneById(request.getModel().getInteger("id"));
		messageThreadId = toDelete.getMessageThread().getId();
		participant = this.repository.findOwner(messageThreadId);
		principal = request.getPrincipal();
		result = participant.getAuthenticated().getId() == principal.getActiveRoleId() && !toDelete.getIsOwner();

		return result;
	}

	@Override
	public void bind(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "isOwner", "messageThread", "authenticated");

	}

	@Override
	public void unbind(final Request<Participant> request, final Participant entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);
	}

	@Override
	public Participant findOne(final Request<Participant> request) {
		assert request != null;

		Participant result;
		int id;

		id = request.getModel().getInteger("id");

		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<Participant> request, final Participant entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
