
package acme.features.employer.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.components.CheckSpam;
import acme.entities.configurations.Configuration;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobPublishService implements AbstractUpdateService<Employer, Job> {

	// Internal state -----------------------------------------------------------------

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		boolean result;
		int jobId;
		Job job;
		Employer employer;
		Principal principal;

		jobId = request.getModel().getInteger("id");
		job = this.repository.findOneJobById(jobId);
		employer = job.getEmployer();
		principal = request.getPrincipal();
		result = job.isFinalMode() || !job.isFinalMode() && employer.getUserAccount().getId() == principal.getAccountId();

		return result;
	}

	@Override
	public void bind(final Request<Job> request, final Job entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "finalMode");

	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "description", "moreInfo", "finalMode");
		model.setAttribute("jobId", request.getModel().getInteger("id"));
	}

	@Override
	public Job findOne(final Request<Job> request) {
		assert request != null;

		Job result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneJobById(id);

		return result;
	}

	@Override
	public void validate(final Request<Job> request, final Job entity, final Errors errors) {

		boolean allDuties100 = false;
		boolean isSpam = true;

		if (!errors.hasErrors("*")) {

			// Checking the description: it's already checked in the Create Service

			// Checking the workload

			Integer workload = 0;
			int jobId;

			jobId = request.getModel().getInteger("id");

			workload = this.repository.findSumPercentageDutiesByJobId(jobId);

			if (workload != null) {

				allDuties100 = workload == 100;

				// Checking the spam

				Configuration configuration = this.repository.findConfiguration();

				// For reference, title, description and moreInfo

				String text = entity.getReference() + "," + entity.getTitle() + "," + entity.getDescription() + "," + entity.getMoreInfo();

				isSpam = CheckSpam.checkSpam(configuration, text);

				// The error if setting to final mode is not possible

				if (!allDuties100 && isSpam) {
					entity.setFinalMode(false);
				}

				errors.state(request, !isSpam && allDuties100, "*", "employer.job.error.finalModeTest");
			} else {
				errors.state(request, workload != null, "*", "employer.job.error.noDuties");
			}
		}

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		entity.setFinalMode(true);
		this.repository.save(entity);

	}

}
