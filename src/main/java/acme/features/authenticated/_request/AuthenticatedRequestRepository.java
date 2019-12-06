
package acme.features.authenticated._request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities._requests._Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedRequestRepository extends AbstractRepository {

	@Query("select r from _Request r where r.deadline > current_timestamp()")
	Collection<_Request> findActiveAll();

	@Query("select r from _Request r where r.id = ?1")
	_Request findOneById(int id);

}
