
package acme.features.authenticated.messageThread;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.messageThread.MessageThread;
import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedMessageThreadShowService implements AbstractShowService<Authenticated, MessageThread> {

	// Internal state -----------------------------------------------------------

	@Autowired
	AuthenticatedMessageThreadRepository repository;


	@Override
	public boolean authorise(final Request<MessageThread> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<MessageThread> request, final MessageThread entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "moment");
		Integer messageThreadId = entity.getId();
		model.setAttribute("messageThreadId", entity.getId());
		model.setAttribute("usersInvolved", this.repository.findInvolvedUsers(messageThreadId));

		// For delete button in form.jsp

		boolean isMine = false;
		Participant owner;
		Principal principal;

		owner = this.repository.findOwner(entity.getId());
		principal = request.getPrincipal();

		if (owner.getAuthenticated().getId() == principal.getActiveRoleId()) {
			isMine = true;
		}

		model.setAttribute("isMine", isMine);

	}

	@Override
	public MessageThread findOne(final Request<MessageThread> request) {
		assert request != null;

		MessageThread result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

}
