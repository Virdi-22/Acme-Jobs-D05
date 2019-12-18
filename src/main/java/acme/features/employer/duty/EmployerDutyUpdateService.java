
package acme.features.employer.duty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.CheckSpam;
import acme.entities.configurations.Configuration;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerDutyUpdateService implements AbstractUpdateService<Employer, Duty> {

	// Internal state ----------------------------------------------------------------------
	@Autowired
	private EmployerDutyRepository repository;


	// AbstractUpdateService<Employer, Duty> interface -------------------------------------

	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;

		boolean result;
		int dutyId;
		Job job;
		Employer employer;
		Principal principal;

		dutyId = request.getModel().getInteger("id");
		job = this.repository.finOneJobByDutyId(dutyId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = job.isFinalMode() || !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Duty> request, final Duty entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "description", "percentage");
		model.setAttribute("jobId", request.getModel().getInteger("jobId"));

	}

	@Override
	public Duty findOne(final Request<Duty> request) {
		assert request != null;

		int dutyId;
		Duty result;

		dutyId = request.getModel().getInteger("id");
		result = this.repository.findOneById(dutyId);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		// Checking if it's spam

		boolean isSpam;

		if (!errors.hasErrors()) {
			Configuration configuration = this.repository.findConfiguration();
			String text = entity.getTitle() + "," + entity.getDescription();
			isSpam = CheckSpam.checkSpam(configuration, text);
			errors.state(request, !isSpam, "*", "employer.duty.error.spam");
		}

	}

	@Override
	public void update(final Request<Duty> request, final Duty entity) {
		assert request != null;

		this.repository.save(entity);
	}

}
