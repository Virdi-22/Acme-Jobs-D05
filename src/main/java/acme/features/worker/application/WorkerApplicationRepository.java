
package acme.features.worker.application;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.applications.Application;
import acme.entities.jobs.Job;
import acme.entities.roles.Worker;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface WorkerApplicationRepository extends AbstractRepository {

	@Query("select j from Application j where j.id = ?1")
	Application findOneById(int id);

	@Query("select j from Application j where j.worker.id = ?1")
	Collection<Application> findManyByWorkerId(int workerId);

	@Query("select j from Job j where j.deadline > current_timestamp() and j.finalMode = 'true'")
	Collection<Job> findActiveJobs();

	@Query("select w from Worker w where w.id=?1")
	Worker findWorker(int id);

	@Query("select j from Job j where j.id=?1")
	Job findJob(int id);

	@Query("select a from Application a where a.reference = ?1")
	Application findOneByReference(String reference);

}
