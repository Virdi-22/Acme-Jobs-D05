
package acme.features.worker.application;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractCreateService;

@Service
public class WorkerApplicationCreateService implements AbstractCreateService<Worker, Application> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	private WorkerApplicationRepository repository;


	// AbstractCreateService<Worker, Application> interface --------------------------------

	@Override
	public boolean authorise(final Request<Application> request) {
		assert request != null;

		Job job = this.repository.findJob(request.getModel().getInteger("jobId"));
		Date now = new Date();

		return job == null || job.getDeadline().after(now) || !job.isFinalMode();
	}

	@Override
	public void bind(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors/* , "creationMoment", "job", "worker", "status" */);
	}

	@Override
	public void unbind(final Request<Application> request, final Application entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "reference", "statement", "skills", "qualifications");
		model.setAttribute("jobId", request.getModel().getInteger("jobId"));
	}

	@Override
	public Application instantiate(final Request<Application> request) {
		Application result;

		result = new Application();

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);
		result.setCreationMoment(moment);
		result.setStatus("Pending");
		result.setWorker(this.repository.findWorker(request.getPrincipal().getActiveRoleId()));
		result.setJob(this.repository.findJob(request.getModel().getInteger("jobId")));

		return result;
	}

	@Override
	public void validate(final Request<Application> request, final Application entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		String reference = entity.getReference().trim();

		boolean otherWithSameReference;

		// Checking the reference

		if (!errors.hasErrors("reference")) {
			otherWithSameReference = this.repository.findOneByReference(reference) == null;
			errors.state(request, otherWithSameReference, "reference", "worker.application.error.reference");

		}

	}

	@Override
	public void create(final Request<Application> request, final Application entity) {

		this.repository.save(entity);
	}

}
