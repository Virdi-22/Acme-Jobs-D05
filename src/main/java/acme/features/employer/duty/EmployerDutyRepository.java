
package acme.features.employer.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.entities.duties.Duty;
import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface EmployerDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findManyByJobId(int jobId);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneById(int id);

	@Query("select j from Job j where j.id = ?1")
	Job findOneJobById(int id);

	@Query("select j from Duty d join d.job j where d.job.id = j.id and d.id = ?1")
	Job finOneJobByDutyId(int id);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

}
