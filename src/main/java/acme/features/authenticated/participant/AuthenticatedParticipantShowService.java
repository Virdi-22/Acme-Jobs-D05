
package acme.features.authenticated.participant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.participants.Participant;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuthenticatedParticipantShowService implements AbstractShowService<Authenticated, Participant> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	AuthenticatedParticipantRepository repository;


	// AbstractShwoService<Authenticated, Participant> interface ---------------------------

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
		Integer messageThreadId = entity.getMessageThread().getId();
		model.setAttribute("messageThreadName", entity.getMessageThread().getTitle());
		model.setAttribute("usersInvolved", this.repository.findInvolvedUsers(messageThreadId));

		// For delete button in form.jsp

		boolean isMine = false;
		Participant owner;
		Principal principal;

		owner = this.repository.findOwner(messageThreadId);
		principal = request.getPrincipal();

		if (owner.getAuthenticated().getId() == principal.getActiveRoleId() && !entity.getIsOwner()) {
			isMine = true;
		}

		model.setAttribute("isMine", isMine);
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

}
