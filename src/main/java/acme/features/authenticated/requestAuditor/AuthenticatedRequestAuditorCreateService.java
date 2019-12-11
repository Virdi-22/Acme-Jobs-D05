
package acme.features.authenticated.requestAuditor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.requestAuditors.RequestAuditor;
import acme.entities.roles.Auditor;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Authenticated;
import acme.framework.entities.Principal;
import acme.framework.entities.UserAccount;
import acme.framework.services.AbstractCreateService;

@Service
public class AuthenticatedRequestAuditorCreateService implements AbstractCreateService<Authenticated, RequestAuditor> {

	// Internal state ----------------------------------------------------------------

	@Autowired
	private AuthenticatedRequestAuditorRepository repository;


	@Override
	public boolean authorise(final Request<RequestAuditor> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<RequestAuditor> request, final RequestAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<RequestAuditor> request, final RequestAuditor entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model);

	}

	@Override
	public RequestAuditor instantiate(final Request<RequestAuditor> request) {
		assert request != null;

		RequestAuditor result;
		Principal principal;
		int userAccountId;
		UserAccount userAccount;

		principal = request.getPrincipal();
		userAccountId = principal.getAccountId();
		userAccount = this.repository.findOneUserAccountById(userAccountId);

		result = new RequestAuditor();
		result.setUserAccount(userAccount);

		return result;
	}

	@Override
	public void validate(final Request<RequestAuditor> request, final RequestAuditor entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

	}

	@Override
	public void create(final Request<RequestAuditor> request, final RequestAuditor entity) {
		assert request != null;
		assert entity != null;

		String firm = request.getModel().getString("firm");
		String responsibilityStatement = request.getModel().getString("responsibilityStatement");

		Auditor auditor = new Auditor();

		auditor.setFirm(firm);
		auditor.setResponsibilityStatement(responsibilityStatement);

		this.repository.save(auditor);

		entity.setAuditor(auditor);

		this.repository.save(entity);

	}

}
