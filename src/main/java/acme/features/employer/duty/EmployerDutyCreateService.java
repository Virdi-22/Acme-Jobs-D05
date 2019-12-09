
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.entities.roles.Employer;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class EmployerDutyCreateService implements AbstractCreateService<Employer, Duty> {

	// Internal state ----------------------------------------------------------------

	@Autowired
	private EmployerDutyRepository repository;


	@Override
	public boolean authorise(final Request<Duty> request) {
		assert request != null;
		return true;
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
	public Duty instantiate(final Request<Duty> request) {
		int jobId;
		Job job;
		Duty result;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		result = new Duty();
		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<Duty> request, final Duty entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		boolean isWorkloadOk;
		int jobId;
		int workload = 0;
		Job job;
		Collection<Duty> dutiesPerJob;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		dutiesPerJob = this.repository.findManyByJobId(jobId);

		if (!errors.hasErrors("percentage")) {
			if (!dutiesPerJob.isEmpty()) {
				for (Duty d : dutiesPerJob) {
					workload = workload + d.getPercentage();
				}
			}
			isWorkloadOk = workload + entity.getPercentage() == 100;
			errors.state(request, isWorkloadOk, "percentage", "employer.duty.error.percentage");
		}
	}

	@Override
	public void create(final Request<Duty> request, final Duty entity) {
		assert request != null;

		int jobId;
		int workload = 0;
		Job job;
		Collection<Duty> dutiesPerJob;

		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		dutiesPerJob = this.repository.findManyByJobId(jobId);

		if (!dutiesPerJob.isEmpty()) {
			for (Duty d : dutiesPerJob) {
				workload = workload + d.getPercentage();
			}
		}
		if (workload == 100) {
			job.setFinalMode(true);
		} else {
			job.setFinalMode(false);
		}

		this.repository.save(entity);

	}

}
