
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.configurations.Configuration;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.services.AbstractUpdateService;

@Service
public class EmployerJobUpdateService implements AbstractUpdateService<Employer, Job> {

	// Internal state ---------------------------------------------------

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

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Job> request, final Job entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "title", "deadline", "salary", "description", "moreInfo");
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
		assert request != null;
		assert entity != null;
		assert errors != null;

		String reference = entity.getReference();
		Calendar calendar;
		Date minimumDeadline;
		Money salary = entity.getSalary();

		boolean otherWithSameReference, isInFuture, isEUR;

		if (!errors.hasErrors("reference")) {
			otherWithSameReference = this.repository.findOneByReference(reference) != null;
			errors.state(request, otherWithSameReference, "reference", "employer.job.error.reference");
		}

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			isInFuture = entity.getDeadline().after(minimumDeadline);
			errors.state(request, isInFuture, "deadline", "employer.job.error.inFuture");
		}

		if (!errors.hasErrors("salary")) {
			isEUR = salary.getCurrency().equals("EUR") || salary.getCurrency().equals("€");
			errors.state(request, isEUR, "salary", "employer.job.error.salary");
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {

		boolean hasDescription = false;
		boolean allDuties100 = false;
		boolean isSpam = true;

		if (!entity.isFinalMode()) {

			// Checking the description

			String description = entity.getDescription();
			int jobId = entity.getId();
			int workload = 0;

			if (description != null && description != "") {
				hasDescription = true;

				// Checking the workload

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

		}

		// Setting job's final mode

		if (hasDescription && allDuties100 && !isSpam) {
			entity.setFinalMode(true);
		}

		this.repository.save(entity);

	}

}
