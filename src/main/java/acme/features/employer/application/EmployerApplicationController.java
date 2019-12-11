
package acme.features.employer.application;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.applications.Application;
import acme.entities.roles.Employer;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/employer/application/")
public class EmployerApplicationController extends AbstractController<Employer, Application> {

	@Autowired
	private EmployerApplicationListService			listService;

	@Autowired
	private EmployerApplicationShowService			showService;

	@Autowired
	private EmployerApplicationUpdateAcceptService	AcceptService;

	@Autowired
	private EmployerApplicationUpdateRejectService	RejectService;


	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addCustomCommand(CustomCommand.UPDATE_ACCEPT, BasicCommand.UPDATE, this.AcceptService);
		super.addCustomCommand(CustomCommand.UPDATE_REJECT, BasicCommand.UPDATE, this.RejectService);
	}
}
