
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.jobs.Job;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractCreateService;

@Service
public class AuditorAuditRecordCreateService implements AbstractCreateService<Auditor, AuditRecord> {

	// Internal state ------------------------------------------------------------------------------

	@Autowired
	private AuditorAuditRecordRepository repository;


	// AbstractCreateService<Auditor, AuditRecord> interface ---------------------------------------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "creationMoment", "status");

	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body");

		model.setAttribute("jobId", request.getModel().getInteger("jobId"));
		model.setAttribute("jobReference", entity.getJob().getReference());

	}

	@Override
	public AuditRecord instantiate(final Request<AuditRecord> request) {
		assert request != null;

		int jobId;
		Job job;
		Principal principal;
		int auditorId;
		Auditor auditor;
		AuditRecord result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		jobId = request.getModel().getInteger("jobId");
		job = this.repository.findOneJobById(jobId);
		principal = request.getPrincipal();
		auditorId = principal.getActiveRoleId();
		auditor = this.repository.findOneAuditorById(auditorId);

		result = new AuditRecord();
		result.setCreationMoment(moment);
		result.setAuditor(auditor);
		result.setJob(job);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setCreationMoment(moment);

		this.repository.save(entity);

	}

}
