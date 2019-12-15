
package acme.features.authenticated.messageThread;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThread.MessageThread;
import acme.entities.participants.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageThreadRepository extends AbstractRepository {

	@Query("select m from MessageThread m where m.id in(select s.messageThread.id from Participant s where s.authenticated.id=?1)")
	Collection<MessageThread> findManyByAuthenticatedId(int authenticatedId);

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findOneById(int id);

	@Query("select u.username from UserAccount u where u.id in (select a.userAccount.id from Authenticated a where a.id in (select m.authenticated.id from Message m where m.messageThread.id = ?1))")
	Collection<String> findInvolvedUsers(int messageThreadId);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticatedById(int id);

	@Query("select p from Participant p where p.isOwner=true and p.messageThread.id = ?1")
	Participant findOwner(int id);
}
