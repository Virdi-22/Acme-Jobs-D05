
package acme.features.authenticated.participant;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.messageThread.MessageThread;
import acme.entities.participants.Participant;
import acme.framework.entities.Authenticated;
import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedParticipantRepository extends AbstractRepository {

	@Query("select p from Participant p where p.id = ?1")
	Participant findOneById(int id);

	@Query("select p from Participant p where p.messageThread.id = ?1")
	Collection<Participant> findManyByMessageThread(int id);

	@Query("select p from Participant p where p.isOwner=true and p.messageThread.id = ?1")
	Participant findOwner(int id);

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findMessageThreadById(int id);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticatedById(int id);

	@Query("select u.username from UserAccount u where u.id in (select a.userAccount.id from Authenticated a where a.id in (select m.authenticated.id from Participant m where m.messageThread.id = ?1))")
	Collection<String> findInvolvedUsers(int messageThreadId);

	@Query("select u from UserAccount u where u.username = ?1")
	UserAccount findUserByName(String userName);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByAccountId(int id);
}
