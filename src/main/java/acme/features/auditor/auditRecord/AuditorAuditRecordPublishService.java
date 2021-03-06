
package acme.features.auditor.auditRecord;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;

@Service
public class AuditorAuditRecordPublishService implements AbstractUpdateService<Auditor, AuditRecord> {

	// Internal state --------------------------------------------------------------------------

	@Autowired
	private AuditorAuditRecordRepository repository;

	// AbstractUpdateService<Auditor, AuditRecord> interface -----------------------------------


	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;

		boolean result;
		int auditRecordId;
		AuditRecord auditRecord;
		Auditor auditor;
		Principal principal;

		auditRecordId = request.getModel().getInteger("id");
		auditRecord = this.repository.findOneById(auditRecordId);
		auditor = auditRecord.getAuditor();
		principal = request.getPrincipal();

		result = auditRecord.isStatus() || !auditRecord.isStatus() && auditor.getUserAccount().getId() == principal.getAccountId();

		return result;
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
		model.setAttribute("jobReference", entity.getJob().getReference());

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		int id;
		AuditRecord result;

		id = request.getModel().getInteger("id");
		result = this.repository.findOneById(id);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void update(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		Date moment;
		moment = new Date(System.currentTimeMillis() - 1);

		entity.setCreationMoment(moment);
		entity.setStatus(true);

		this.repository.save(entity);
	}

}
