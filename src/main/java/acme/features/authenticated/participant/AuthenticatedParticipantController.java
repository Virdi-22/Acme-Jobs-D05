
package acme.features.authenticated.participant;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.participants.Participant;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/participant/")
public class AuthenticatedParticipantController extends AbstractController<Authenticated, Participant> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	private AuthenticatedParticipantListByMessageThreadService	listByMessageThreadService;

	@Autowired
	private AuthenticatedParticipantCreateService				createService;

	@Autowired
	private AuthenticatedParticipantDeleteService				deleteService;

	@Autowired
	private AuthenticatedParticipantShowService					showService;


	// Cosntructors ------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_BY_MESSAGE_THREAD, BasicCommand.LIST, this.listByMessageThreadService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
