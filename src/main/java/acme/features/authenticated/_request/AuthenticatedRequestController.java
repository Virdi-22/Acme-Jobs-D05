
package acme.features.authenticated._request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities._requests._Request;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Authenticated;

@Controller
@RequestMapping("/authenticated/_-request/")
public class AuthenticatedRequestController extends AbstractController<Authenticated, _Request> {

	// Internal state

	@Autowired
	private AuthenticatedRequestListService	listService;

	@Autowired
	private AuthenticatedRequestShowService	showService;


	// Constructors

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
	}
}
