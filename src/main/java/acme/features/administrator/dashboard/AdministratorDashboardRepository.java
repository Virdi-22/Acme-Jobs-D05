
package acme.features.administrator.dashboard;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(a) from Announcement a")
	Double totalNumberOfAnnouncements();

	@Query("select count(c) from CompanyRecord c")
	Double totalNumberOfCompanyRecords();

	@Query("select count(i) from InvestorRecord i")
	Double totalNumberOfInvestorRecords();

	@Query("select min(r.reward.amount) from _Request r where r.deadline > CURRENT_DATE()")
	Double minimumRequestRewards();

	@Query("select max(r.reward.amount) from _Request r where r.deadline > CURRENT_DATE()")
	Double maximumRequestRewards();

	@Query("select avg(r.reward.amount) from _Request r where r.deadline > CURRENT_DATE()")
	Double averageRequestRewards();

	@Query("select stddev(r.reward.amount) from _Request r where r.deadline > CURRENT_DATE()")
	Double standardDeviationRequestRewards();

	@Query("select min(o.minMoney.amount) from Offer o where o.deadline > CURRENT_DATE()")
	Double minimumOfferRewards();

	@Query("select max(o.maxMoney.amount) from Offer o where o.deadline > CURRENT_DATE()")
	Double maximumOfferRewards();

	@Query("select avg((o.minMoney.amount + o.maxMoney.amount)/2) from Offer o where o.deadline > CURRENT_DATE()")
	Double averageOfferRewards();

	@Query("select stddev((o.minMoney.amount + o.maxMoney.amount)/2) from Offer o where o.deadline > CURRENT_DATE()")
	Double standardDeviationOfferRewards();

	@Query("select c.sector from CompanyRecord c group by c.sector")
	Object[] getCompanySectors();

	@Query("select count(c) from CompanyRecord c group by c.sector")
	Object[] getTotalCompanyRecordsBySector();

	@Query("select i.sector from InvestorRecord i group by i.sector")
	Object[] getInvestorSectors();

	@Query("select count(i) from InvestorRecord i group by i.sector")
	Object[] getTotalInvestorRecordsBySector();

	// D04 Complex Reporting
	@Query("select avg(select count(j) from Job j where j.employer.id = e.id) from Employer e")
	Double averageNumberOfJobsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.job.employer.id = e.id) from Employer e")
	Double averageNumberOfApplicationsPerEmployer();

	@Query("select avg(select count(a) from Application a where a.worker.id = w.id) from Worker w")
	Double averageNumberOfApplicationsPerWorker();

	@Query("select j.finalMode from Job j group by j.finalMode")
	Object[] getJobStatus();

	@Query("select count(j) from Job j")
	Double totalJobs();

	@Query("select count(j) from Job j group by j.finalMode")
	Object[] getAverageJobsByStatus();

	@Query("select a.status from Application a group by a.status")
	Object[] getApplicationStatus();

	@Query("select count(a) from Application a")
	Double totalApplications();

	@Query("select count(a) from Application a group by a.status")
	Object[] getAverageApplicationsByStatus();

	// D05 Complex Edition

	@Query("select a from Application a where a.creationMoment >= ?2 and a.creationMoment < ?1")
	List<Application> findAllApplicationsByDay(Date highRange, Date lowRange);

	@Query("select a from Application a")
	List<Application> findAllApplications();
}
