
package acme.features.administrator.auditor;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.requestAuditors.RequestAuditor;
import acme.entities.roles.Auditor;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorAuditorRepository extends AbstractRepository {

	@Query("select a from Auditor a where a.userAccount = null")
	Collection<Auditor> findManyAll();

	@Query("select a from Auditor a where a.id = ?1")
	Auditor findOneAuditorById(int id);

	@Query("select ua from RequestAuditor ra join ra.userAccount ua where ra.auditor.id = ?1")
	UserAccount findOneUserAccountByAuditorId(int id);

	@Query("select ra from RequestAuditor ra where ra.auditor.id = ?1")
	RequestAuditor findOneRequestAuditorByAuditorId(int id);

}
