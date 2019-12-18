
package acme.features.provider._request;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities._requests._Request;
import acme.entities.roles.Provider;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/provider/_-request/")
public class ProviderRequestController extends AbstractController<Provider, _Request> {

	// Internal state --------------------------------------------------------------------

	@Autowired
	private ProviderRequestListService		listService;

	@Autowired
	private ProviderRequestShowService		showService;

	@Autowired
	private ProviderRequestCreateService	createService;


	// Constructors ----------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
	}

}
