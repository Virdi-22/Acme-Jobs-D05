
package acme.features.auditor.job;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.jobs.Job;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorJobRepository extends AbstractRepository {

	@Query("select j from AuditRecord a join a.job j where a.auditor.id = ?1 and j.finalMode = true")
	Collection<Job> findManyJobsMyAuditRecords(int auditorId);

	@Query("select j from Job j where not exists(select a from AuditRecord a where a.job.id = j.id and a.auditor.id = ?1) and j.finalMode = true")
	Collection<Job> findManyJobsNotMyAuditRecords(int auditorId);

	@Query("select j from Job j where j.id = ?1")
	Job findOneById(int id);

}
