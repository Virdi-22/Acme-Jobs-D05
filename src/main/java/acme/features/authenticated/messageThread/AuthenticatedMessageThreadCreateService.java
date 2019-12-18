
package acme.features.authenticated.messageThread;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.MessageThread;
import acme.entities.participants.Participant;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageThreadCreateService implements AbstractCreateService<Authenticated, MessageThread> {

	// Internal state ------------------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	// AbstractCreateService<Authenticated, MessageThread> interface -------------------

	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment");
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title");

	}

	@Override
	public MessageThread instantiate(final Request<MessageThread> request) {
		MessageThread result;
		Date moment;

		result = new MessageThread();
		moment = new Date(System.currentTimeMillis() - 1);

		result.setMoment(moment);

		return result;
	}

	@Override
	public void validate(final Request<MessageThread> request, final MessageThread entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<MessageThread> request, final MessageThread entity) {

		this.repository.save(entity);

		Participant owner = new Participant();
		owner.setIsOwner(true);
		owner.setAuthenticated(this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId()));
		owner.setMessageThread(this.repository.findOneById(entity.getId()));

		this.repository.save(owner);

	}
}
