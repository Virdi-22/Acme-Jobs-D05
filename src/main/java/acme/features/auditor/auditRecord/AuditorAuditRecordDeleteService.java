
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;

@Service
public class AuditorAuditRecordDeleteService implements AbstractDeleteService<Auditor, AuditRecord> {

	// Internal state -----------------------------------------------------------------------------

	@Autowired
	private AuditorAuditRecordRepository repository;


	// AbstractDeleteService<Auditor, AuditRecord> interface --------------------------------------

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

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body");

	}

	@Override
	public AuditRecord findOne(final Request<AuditRecord> request) {
		assert request != null;

		int auditRecordId;
		AuditRecord result;

		auditRecordId = request.getModel().getInteger("id");
		result = this.repository.findOneById(auditRecordId);

		return result;
	}

	@Override
	public void validate(final Request<AuditRecord> request, final AuditRecord entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void delete(final Request<AuditRecord> request, final AuditRecord entity) {
		assert request != null;
		assert entity != null;

		this.repository.delete(entity);

	}

}
