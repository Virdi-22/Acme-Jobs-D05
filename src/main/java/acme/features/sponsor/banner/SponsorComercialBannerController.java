
package acme.features.sponsor.banner;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.banners.ComercialBanner;
import acme.entities.roles.Sponsor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/sponsor/comercial-banner/")
public class SponsorComercialBannerController extends AbstractController<Sponsor, ComercialBanner> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	private SponsorComercialBannerListMineService	listMineService;

	@Autowired
	private SponsorComercialBannerShowService		showService;

	@Autowired
	private SponsorComercialBannerCreateService		createService;

	@Autowired
	private SponsorComercialBannerUpdateService		updateService;

	@Autowired
	private SponsorComercialBannerDeleteService		deleteService;


	// Constructors ------------------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);
	}
}
