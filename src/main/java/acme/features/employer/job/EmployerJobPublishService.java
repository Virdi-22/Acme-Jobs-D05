
package acme.features.employer.job;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobPublishService implements AbstractUpdateService<Employer, Job> {

	// Internal state -----------------------------------------------------------------

	@Autowired
	private EmployerJobRepository repository;


	@Override
	public boolean authorise(final Request<Job> request) {
		assert request != null;

		return true;
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

		request.getModel().setAttribute("jobId", entity.getId());

		boolean allDuties100 = false;
		boolean isSpam = true;

		if (!errors.hasErrors("finalMode")) {

			// Checking the description: it's already checked in the Create Service

			// Checking the workload

			int workload = 0;
			int jobId;

			jobId = entity.getId();

			Collection<Duty> dutiesPerJob = this.repository.findAllDutiesByJobId(jobId);

			if (!dutiesPerJob.isEmpty()) {
				for (Duty d : dutiesPerJob) {
					workload = workload + d.getPercentage();
				}
			}
			if (workload == 100) {
				allDuties100 = true;
			}
		}

		// Checking the spam

		Configuration configuration = this.repository.findConfiguration();
		String spam = configuration.getSpamWordsListing();

		// For reference, title, description and moreInfo

		CharSequence referenceSequence = entity.getReference().subSequence(0, entity.getReference().length());
		CharSequence titleSequence = entity.getTitle().subSequence(0, entity.getTitle().length());
		CharSequence descriptionSequence = entity.getDescription().subSequence(0, entity.getDescription().length());
		CharSequence moreInfo = entity.getMoreInfo().subSequence(0, entity.getMoreInfo().length());

		isSpam = spam.contains(referenceSequence) && spam.contains(titleSequence) && spam.contains(descriptionSequence) && spam.contains(moreInfo);

		// The error if setting to final mode is not possible

		if (!allDuties100 && isSpam) {
			entity.setFinalMode(false);
		}

		errors.state(request, !isSpam && allDuties100, "finalMode", "employer.job.error.finalModeTest");

	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		entity.setFinalMode(true);
		this.repository.save(entity);

	}

}
