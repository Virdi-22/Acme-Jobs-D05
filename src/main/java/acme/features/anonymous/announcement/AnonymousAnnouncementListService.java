
package acme.features.anonymous.announcement;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractListService;

@Service
public class AnonymousAnnouncementListService implements AbstractListService<Anonymous, Announcement> {

	// Internal state ----------------------------------------------------------------------

	@Autowired
	AnonymousAnnouncementRepository repository;


	// AbstractListService<Anonymous, Announcement> interface ------------------------------

	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;
		return true;
	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "moment", "title");
	}

	@Override
	public Collection<Announcement> findMany(final Request<Announcement> request) {
		assert request != null;

		Collection<Announcement> result;

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);

		Date dateSearch = calendar.getTime();

		result = this.repository.findNoOlderThanAMonth(dateSearch);

		return result;
	}

}
