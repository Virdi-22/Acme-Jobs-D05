
package acme.features.employer.job;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.getModel().setAttribute("jobId", entity.getId());

		String reference = entity.getReference();
		Calendar calendar;
		Date minimumDeadline;
		Money salary = entity.getSalary();

		boolean otherWithSameReference, isInFuture, isEUR;

		// Checking the reference

		if (!errors.hasErrors("reference")) {
			otherWithSameReference = this.repository.findOneByReference(reference) != null;
			errors.state(request, otherWithSameReference, "reference", "employer.job.error.reference");
		}

		// Checking the deadline

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			isInFuture = entity.getDeadline().after(minimumDeadline);
			errors.state(request, isInFuture, "deadline", "employer.job.error.inFuture");
		}

		// Checking the salary

		if (!errors.hasErrors("salary")) {
			isEUR = salary.getCurrency().equals("EUR") || salary.getCurrency().equals("€");
			errors.state(request, isEUR, "salary", "employer.job.error.salary");
		}
	}

	@Override
	public void update(final Request<Job> request, final Job entity) {
		assert request != null;
		this.repository.save(entity);

	}

}
