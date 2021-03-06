
package acme.features.auditor.duty;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.duties.Duty;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuditorDutyRepository extends AbstractRepository {

	@Query("select d from Duty d where d.job.id = ?1")
	Collection<Duty> findManyByJobId(int jobId);

	@Query("select d from Duty d where d.id = ?1")
	Duty findOneById(int id);

}
