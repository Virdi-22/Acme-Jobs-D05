
package acme.features.administrator.auditor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/auditor/")
public class AdministratorAuditorController extends AbstractController<Administrator, Auditor> {

	// Internal state -------------------------------------------------------------------------

	@Autowired
	private AdministratorAuditorListService		listService;

	@Autowired
	private AdministratorAuditorShowService		showService;

	@Autowired
	private AdministratorAuditorAcceptService	acceptService;

	@Autowired
	private AdministratorAuditorRejectService	rejectService;


	// Constructors ---------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);

		super.addCustomCommand(CustomCommand.ACCEPT, BasicCommand.UPDATE, this.acceptService);
		super.addCustomCommand(CustomCommand.REJECT, BasicCommand.UPDATE, this.rejectService);
	}

}
