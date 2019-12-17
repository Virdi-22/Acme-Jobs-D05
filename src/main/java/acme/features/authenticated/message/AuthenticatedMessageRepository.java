
package acme.features.authenticated.message;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.configurations.Configuration;
import acme.entities.message.Message;
import acme.entities.messageThread.MessageThread;
import acme.framework.entities.Authenticated;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedMessageRepository extends AbstractRepository {

	@Query("select m from Message m where m.id = ?1")
	Message findOneById(int id);

	@Query("select m from Message m where m.messageThread.id = ?1")
	Collection<Message> findManyByMessageThreadId(int messageThreadId);

	@Query("select u.username from UserAccount u where u.id in (select a.userAccount.id from Authenticated a where a.id in (select m.authenticated.id from Message m where m.id = ?1))")
	String findUser(int messageId);

	@Query("select m from MessageThread m where m.id = ?1")
	MessageThread findMessageThreadById(int id);

	@Query("select a from Authenticated a where a.id=?1")
	Authenticated findAuthenticatedById(int id);

	@Query("select c from Configuration c")
	Configuration findConfiguration();

	@Query("select u.username from UserAccount u where u.id in (select a.userAccount.id from Authenticated a where a.id in (select m.authenticated.id from Participant m where m.messageThread.id = ?1))")
	Collection<String> findInvolvedUsers(int messageThreadId);

	@Query("select a from Authenticated a where a.userAccount.id = ?1")
	Authenticated findAuthenticatedByAccountId(int id);
}
