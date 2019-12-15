
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.participants.Participant;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipantRepository extends AbstractRepository {

	@Query("select p from Participant p where p.id = ?1")
	Participant findOneById(int id);

	@Query("select p from Participant p where p.messageThread.id = ?1")
	Collection<Participant> findManyByMessageThread(int id);

	@Query("select p from Participant p where p.isOwner=true")
	Participant findOwner();
}
