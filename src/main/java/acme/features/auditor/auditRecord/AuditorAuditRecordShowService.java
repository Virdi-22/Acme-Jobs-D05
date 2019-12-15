
package acme.features.auditor.auditRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;

@Service
public class AuditorAuditRecordShowService implements AbstractShowService<Auditor, AuditRecord> {

	// Internal state -------------------------------------------------------------------------

	@Autowired
	private AuditorAuditRecordRepository repository;


	// AbstractShowService<Auditor, AuditRecord> interface ------------------------------------

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
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "body", "creationMoment", "status");
		model.setAttribute("jobReference", entity.getJob().getReference());

		// For delete button in form.jsp

		boolean isMine = false;
		Principal principal;
		int auditorId;
		Auditor auditor;

		principal = request.getPrincipal();
		auditorId = principal.getActiveRoleId();
		auditor = this.repository.findOneAuditorById(auditorId);

		if (entity.getAuditor().getId() == auditor.getId()) {
			isMine = true;
		}
		model.setAttribute("isMine", isMine);

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

}
