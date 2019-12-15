
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedParticipantCreateService implements AbstractCreateService<Authenticated, Participant> {

	@Autowired
	AuthenticatedParticipantRepository repository;


	@Override
	public boolean authorise(final Request<Participant> request) {
		assert request != null;
		boolean result;
		int messageThreadId;
		Participant participant;
		Principal principal;

		messageThreadId = request.getModel().getInteger("messageThreadId");
		participant = this.repository.findOwner(messageThreadId);
		principal = request.getPrincipal();
		result = participant.getAuthenticated().getId() == principal.getActiveRoleId();

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
		Integer messageThreadId = model.getInteger("messageThreadId");
		model.setAttribute("messageThreadId", messageThreadId);
		model.setAttribute("messageThreadName", this.repository.findMessageThreadById(messageThreadId).getTitle());
		model.setAttribute("usersInvolved", this.repository.findInvolvedUsers(messageThreadId));

	}

	@Override
	public Participant instantiate(final Request<Participant> request) {
		Participant result = new Participant();

		result.setIsOwner(false);
		result.setAuthenticated(this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId()));
		Integer messageThreadId = request.getModel().getInteger("messageThreadId");
		result.setMessageThread(this.repository.findMessageThreadById(messageThreadId));

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<Participant> request, final Participant entity) {

		this.repository.save(entity);

	}

}
