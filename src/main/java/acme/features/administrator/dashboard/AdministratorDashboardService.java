
package acme.features.administrator.dashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.Dashboard;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardService implements AbstractShowService<Administrator, Dashboard> {

	// Internal state -------------------------------------------------------------------------------

	@Autowired
	private AdministratorDashboardRepository repository;


	// AbstractShowService<Administrator, Dashboard> interface --------------------------------------

	@Override
	public boolean authorise(final Request<Dashboard> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Dashboard> request, final Dashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "totalNumberOfAnnouncements", "totalNumberOfCompanyRecords", "totalNumberOfInvestorRecords", "minimumRequestRewards", "maximumRequestRewards", "averageRequestRewards", "standardDeviationRequestRewards",
			"minimumOfferRewards", "maximumOfferRewards", "averageofferRewards", "standardDeviationOfferRewards", "companySectors", "totalCompaniesBySector", "investorSectors", "totalInvestorsBySector", "averageNumberOfJobsPerEmployer",
			"averageNumberOfApplicationsPerEmployer", "averageNumberOfApplicationsPerWorker", "jobStatus", "averageJobsByStatus", "applicationStatus", "averageApplicationsByStatus");
	}

	@Override
	public Dashboard findOne(final Request<Dashboard> request) {
		assert request != null;

		Dashboard result = new Dashboard();

		result.setTotalNumberOfAnnouncements(this.repository.totalNumberOfAnnouncements());
		result.setTotalNumberOfCompanyRecords(this.repository.totalNumberOfCompanyRecords());
		result.setTotalNumberOfInvestorRecords(this.repository.totalNumberOfInvestorRecords());

		result.setMaximumRequestRewards(this.repository.maximumRequestRewards());
		result.setMinimumRequestRewards(this.repository.minimumRequestRewards());
		result.setAverageRequestRewards(this.repository.averageRequestRewards());
		result.setStandardDeviationRequestRewards(this.repository.standardDeviationRequestRewards());

		result.setMaximumOfferRewards(this.repository.maximumOfferRewards());
		result.setMinimumOfferRewards(this.repository.minimumOfferRewards());

		result.setAverageofferRewards(this.repository.averageOfferRewards());
		result.setStandardDeviationOfferRewards(this.repository.standardDeviationOfferRewards());

		result.setCompanySectors(this.repository.getCompanySectors());
		result.setTotalCompaniesBySector(this.repository.getTotalCompanyRecordsBySector());
		result.setInvestorSectors(this.repository.getInvestorSectors());
		result.setTotalInvestorsBySector(this.repository.getTotalInvestorRecordsBySector());

		result.setAverageNumberOfJobsPerEmployer(this.repository.averageNumberOfJobsPerEmployer());
		result.setAverageNumberOfApplicationsPerEmployer(this.repository.averageNumberOfApplicationsPerEmployer());
		result.setAverageNumberOfApplicationsPerWorker(this.repository.averageNumberOfApplicationsPerWorker());

		result.setJobStatus(this.repository.getJobStatus());

		Object[] jobsByStatus = this.repository.getAverageJobsByStatus();
		Double totalJobs = this.repository.totalJobs();
		Object[] jobResult = new Object[jobsByStatus.length + 1];
		Integer j = 0;
		for (Object i : jobsByStatus) {
			Long x = (Long) i;
			jobResult[j] = x / totalJobs;
			j++;
		}
		result.setAverageJobsByStatus(jobResult);

		result.setApplicationStatus(this.repository.getApplicationStatus());

		Object[] applicationsByStatus = this.repository.getAverageApplicationsByStatus();
		Double totalApplications = this.repository.totalApplications();
		Object[] AppResult = new Object[applicationsByStatus.length + 1];
		Integer k = 0;
		for (Object i : applicationsByStatus) {
			Long x = (Long) i;
			AppResult[k] = x / totalApplications;
			k++;
		}
		result.setAverageApplicationsByStatus(AppResult);

		return result;
	}
}
