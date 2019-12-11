
package acme.features.administrator.auditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestAuditors.RequestAuditor;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorAuditorRejectService implements AbstractUpdateService<Administrator, Auditor> {

	// Internal state ---------------------------------------------------------------------------

	@Autowired
	private AdministratorAuditorRepository repository;


	@Override
	public boolean authorise(final Request<Auditor> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Auditor> request, final Auditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);

	}

	@Override
	public Auditor findOne(final Request<Auditor> request) {
		assert request != null;

		int auditorId;
		Auditor result;

		auditorId = request.getModel().getInteger("id");
		result = this.repository.findOneAuditorById(auditorId);

		return result;
	}

	@Override
	public void validate(final Request<Auditor> request, final Auditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void update(final Request<Auditor> request, final Auditor entity) {
		assert request != null;
		assert entity != null;

		int auditorId;
		RequestAuditor result;

		auditorId = entity.getId();
		result = this.repository.findOneRequestAuditorByAuditorId(auditorId);

		this.repository.delete(entity);
		this.repository.delete(result);

	}

}
