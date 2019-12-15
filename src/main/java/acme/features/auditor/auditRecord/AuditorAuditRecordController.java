
package acme.features.auditor.auditRecord;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.components.CustomCommand;
import acme.entities.auditRecords.AuditRecord;
import acme.entities.roles.Auditor;
import acme.framework.components.BasicCommand;
import acme.framework.controllers.AbstractController;

@Controller
@RequestMapping("/auditor/audit-record/")
public class AuditorAuditRecordController extends AbstractController<Auditor, AuditRecord> {

	// Internal state ---------------------------------------------------------------

	@Autowired
	private AuditorAuditRecordListMineService	listMineService;

	@Autowired
	private AuditorAuditRecordShowService		showService;

	@Autowired
	private AuditorAuditRecordCreateService		createService;

	@Autowired
	private AuditorAuditRecordUpdateService		updateService;

	@Autowired
	private AuditorAuditRecordDeleteService		deleteService;

	@Autowired
	private AuditorAuditRecordPublishService	publishService;


	// Constructors -----------------------------------------------------------------

	@PostConstruct
	private void initialise() {
		super.addBasicCommand(BasicCommand.SHOW, this.showService);
		super.addBasicCommand(BasicCommand.CREATE, this.createService);
		super.addBasicCommand(BasicCommand.UPDATE, this.updateService);
		super.addBasicCommand(BasicCommand.DELETE, this.deleteService);

		super.addCustomCommand(CustomCommand.LIST_MINE, BasicCommand.LIST, this.listMineService);
		super.addCustomCommand(CustomCommand.PUBLISH, BasicCommand.UPDATE, this.publishService);
	}

}
