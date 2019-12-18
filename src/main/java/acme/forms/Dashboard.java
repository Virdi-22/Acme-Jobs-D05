
package acme.forms;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dashboard implements Serializable {

	// Serialisation identifier ------------------------------------

	private static final long	serialVersionUID	= 1L;

	// Attributed --------------------------------------------------

	Double						totalNumberOfAnnouncements;
	Double						totalNumberOfCompanyRecords;
	Double						totalNumberOfInvestorRecords;
	Double						minimumRequestRewards;
	Double						maximumRequestRewards;
	Double						averageRequestRewards;
	Double						standardDeviationRequestRewards;
	Double						minimumOfferRewards;
	Double						maximumOfferRewards;
	Double						averageofferRewards;
	Double						standardDeviationOfferRewards;

	Object[]					companySectors;
	Object[]					totalCompaniesBySector;
	Object[]					investorSectors;
	Object[]					totalInvestorsBySector;

	// D04 Complex Reporting

	Double						averageNumberOfJobsPerEmployer;
	Double						averageNumberOfApplicationsPerEmployer;
	Double						averageNumberOfApplicationsPerWorker;

	Object[]					jobStatus;
	Object[]					averageJobsByStatus;
	Object[]					applicationStatus;
	Object[]					averageApplicationsByStatus;

	// D05 Complex Editing

	List<Integer>				acceptedApplicationsPerDay;
	List<Integer>				rejectedApplicationsPerDay;
	List<Integer>				pendingApplicationsPerDay;

	List<String>				closestDays;
}
