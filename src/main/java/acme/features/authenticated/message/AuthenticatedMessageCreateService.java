
package acme.features.authenticated.message;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.CheckSpam;
import acme.entities.configurations.Configuration;
import acme.entities.message.Message;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedMessageCreateService implements AbstractCreateService<Authenticated, Message> {

	@Autowired
	AuthenticatedMessageRepository repository;


	@Override
	public boolean authorise(final Request<Message> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "moment", "messageThread", "authenticated");

	}

	@Override
	public void unbind(final Request<Message> request, final Message entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "tags");
		model.setAttribute("messageThreadId", request.getModel().getInteger("messageThreadId"));

		if (request.isMethod(HttpMethod.GET)) {
			model.setAttribute("check", "false");
		} else {
			request.transfer(model, "check");
		}
	}

	@Override
	public Message instantiate(final Request<Message> request) {
		Message result;
		Date moment;

		result = new Message();

		moment = new Date(System.currentTimeMillis() - 1);

		result.setMoment(moment);
		result.setAuthenticated(this.repository.findAuthenticatedById(request.getPrincipal().getActiveRoleId()));
		result.setMessageThread(this.repository.findMessageThreadById(request.getModel().getInteger("messageThreadId")));

		return result;
	}

	@Override
	public void validate(final Request<Message> request, final Message entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isSpam, isAccepted;

		if (!errors.hasErrors()) {
			Configuration configuration = this.repository.findConfiguration();
			String text = entity.getTitle() + "," + entity.getBody() + "," + entity.getTags();
			isSpam = CheckSpam.checkSpam(configuration, text);
			errors.state(request, !isSpam, "*", "employer.job.error.spam");
		}

		// Validating the checkbox

		String res = request.getModel().getString("check");
		isAccepted = res.equals("true");
		errors.state(request, isAccepted, "check", "provider.request.error.must-accept");

	}

	@Override
	public void create(final Request<Message> request, final Message entity) {
		assert request != null;

		this.repository.save(entity);

	}

}
