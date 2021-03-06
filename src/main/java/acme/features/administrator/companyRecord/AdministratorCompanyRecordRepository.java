
package acme.features.administrator.companyRecord;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.companyRecords.CompanyRecord;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorCompanyRecordRepository extends AbstractRepository {

	@Query("Select a from CompanyRecord a where a.id = ?1")
	CompanyRecord findOneCompanyRecordById(int id);

	@Query("Select a from CompanyRecord a")
	Collection<CompanyRecord> findManyCompanyRecords();

}
