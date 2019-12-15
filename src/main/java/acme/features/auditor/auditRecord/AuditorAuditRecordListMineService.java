
package acme.features.auditor.auditRecord;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.services.AbstractListService;

@Service
public class AuditorAuditRecordListMineService implements AbstractListService<Auditor, AuditRecord> {

	// Internal state --------------------------------------------------------------------------------

	@Autowired
	private AuditorAuditRecordRepository repository;


	// AbstractListService<Auditor, AuditRecord> interface -------------------------------------------

	@Override
	public boolean authorise(final Request<AuditRecord> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<AuditRecord> request, final AuditRecord entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "creationMoment", "title");

	}

	@Override
	public Collection<AuditRecord> findMany(final Request<AuditRecord> request) {
		assert request != null;

		int auditorId;
		Collection<AuditRecord> result;

		auditorId = request.getPrincipal().getActiveRoleId();
		result = this.repository.findManyByAuditorId(auditorId);

		return result;
	}

}
