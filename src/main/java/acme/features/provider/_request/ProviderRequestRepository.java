
package acme.features.provider._request;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities._requests._Request;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface ProviderRequestRepository extends AbstractRepository {

	@Query("select r from _Request r")
	Collection<_Request> findAllRequests();

	@Query("select r from _Request r where r.deadline > current_timestamp()")
	Collection<_Request> findActiveRequests();

	@Query("select r from _Request r where r.id = ?1")
	_Request findOneById(int id);

	@Query("select r from _Request r where r.ticker = ?1")
	_Request findOneByTicker(String ticker);
}
