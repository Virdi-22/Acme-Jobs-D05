
package acme.features.employer.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.helpers.StringHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerApplicationUpdateService implements AbstractUpdateService<Employer, Application> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	EmployerApplicationRepository repository;


	// AbstractUpdateService<Employer, Application> interface ------------------------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;
		boolean result;
		int applicationId;
		Application application;
		Employer employer;
		Principal principal;

		applicationId = request.getModel().getInteger("id");
		application = this.repository.findOneById(applicationId);
		employer = application.getJob().getEmployer();
		principal = request.getPrincipal();
		result = employer.getUserAccount().getId() == principal.getAccountId();
		return result;
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
		request.getModel().setAttribute("jobReference", entity.getJob().getReference());
		request.getModel().setAttribute("jobInfo", entity.getJob().getMoreInfo());

	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "reasonRejected");
	}

	@Override
	public Application findOne(final Request<Application> request) {
		assert request != null;

		Application result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String reasonRejected;
		boolean hasReason;
		if (request.getModel().getAttribute("status").equals("Rejected")) {
			if (!errors.hasErrors("reasonRejected")) {
				reasonRejected = request.getModel().getString("reasonRejected");
				hasReason = !StringHelper.isBlank(reasonRejected);

				errors.state(request, hasReason, "reasonRejected", "employer.application.reasonRejected.error");
			}
		}

	}

	@Override
	public void update(final Request<Application> request, final Application entity) {
		assert request != null;
		assert entity != null;
		entity.setStatus(request.getModel().getString("status"));
		this.repository.save(entity);

	}
}
