
package acme.features.administrator.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.banners.ComercialBanner;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;
import acme.framework.entities.Administrator;

@Controller
@RequestMapping("/administrator/comercial-banner/")
public class AdministratorComercialBannerController extends AbstractController<Administrator, ComercialBanner> {

	@Autowired
	private AdministratorComercialBannerListByCreditCardService	listService;

	@Autowired
	private AdministratorComercialBannerShowService				showService;

	@Autowired
	private AdministratorComercialBannerUpdateService			updateService;

	@Autowired
	private AdministratorComercialBannerCreateService			createService;

	@Autowired
	private AdministratorComercialBannerDeleteService			deleteService;


	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_BY_CREDIT, BasicCommand.LIST, this.listService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}

}
