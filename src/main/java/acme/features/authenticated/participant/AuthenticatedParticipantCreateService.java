
package acme.features.authenticated.participant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
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
		Integer messageThreadId = request.getModel().getInteger("messageThreadId");
		model.setAttribute("messageThreadId", messageThreadId);
		model.setAttribute("messageThreadName", this.repository.findMessageThreadById(messageThreadId).getTitle());
		model.setAttribute("usersInvolved", this.repository.findInvolvedUsers(messageThreadId));

	}

	@Override
	public Participant instantiate(final Request<Participant> request) {
		Participant result = new Participant();

		result.setIsOwner(false);
		Integer messageThreadId = request.getModel().getInteger("messageThreadId");
		result.setMessageThread(this.repository.findMessageThreadById(messageThreadId));
		result.setAuthenticated(this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId()));

		return result;
	}

	@Override
	public void validate(final Request<Participant> request, final Participant entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isNull;

		if (!errors.hasErrors("userName")) {

			String userName = request.getModel().getString("userName");
			UserAccount aux = this.repository.findUserByName(userName);
			List<String> isAlreadyAParticipant = (List<String>) this.repository.findInvolvedUsers(request.getModel().getInteger("messageThreadId"));
			isNull = aux == null || isAlreadyAParticipant.contains(aux.getUsername());
			errors.state(request, !isNull, "*", "authenticated.participant.error.name");
		}

	}

	@Override
	public void create(final Request<Participant> request, final Participant entity) {

		String userName = request.getModel().getString("userName");
		UserAccount aux = this.repository.findUserByName(userName);
		Authenticated auth = this.repository.findAuthenticatedByAccountId(aux.getId());
		entity.setAuthenticated(auth);

		this.repository.save(entity);

	}

}
