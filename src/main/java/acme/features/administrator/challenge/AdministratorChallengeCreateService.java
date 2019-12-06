
package acme.features.administrator.challenge;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.challenges.Challenge;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.datatypes.Money;
import acme.framework.entities.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorChallengeCreateService implements AbstractCreateService<Administrator, Challenge> {

	// Internal state -------------------------------------------------------------

	@Autowired
	AdministratorChallengeRepository repository;

	// AdministratorUpdateService<Administrator, Offer> interface -----------------


	@Override
	public boolean authorise(final Request<Challenge> request) {
		assert request != null;
		return true;
	}

	@Override
	public void bind(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);

	}

	@Override
	public void unbind(final Request<Challenge> request, final Challenge entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "deadline", "description", "bronzeGoal", "bronzeReward", "silverGoal", "silverReward", "goldGoal", "goldReward");

	}

	@Override
	public Challenge instantiate(final Request<Challenge> request) {
		Challenge result;
		result = new Challenge();
		return result;
	}

	@Override
	public void validate(final Request<Challenge> request, final Challenge entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		Calendar calendar;
		Date minimumDeadline;
		Money bronzeReward = entity.getBronzeReward();
		Money silverReward = entity.getSilverReward();
		Money goldReward = entity.getGoldReward();
		boolean isEURbronzeReward, isEURsilverReward, isEURgoldReward, isSilverMoreThanBronze, isGoldMoreThanSilver;

		if (!errors.hasErrors("deadline")) {
			calendar = new GregorianCalendar();
			minimumDeadline = calendar.getTime();
			boolean isInFuture = entity.getDeadline().after(minimumDeadline);
			errors.state(request, isInFuture, "deadline", "administrator.challenge.error.inFuture");
		}

		if (!errors.hasErrors("bronzeReward") || !errors.hasErrors("silverReward") || !errors.hasErrors("goldReward")) {
			isEURbronzeReward = bronzeReward.getCurrency().equals("EUR") || bronzeReward.getCurrency().equals("€");
			errors.state(request, isEURbronzeReward, "bronzeReward", "administrator.challenge.error.EURbronzeReward");

			isEURsilverReward = silverReward.getCurrency().equals("EUR") || silverReward.getCurrency().equals("€");
			errors.state(request, isEURsilverReward, "silverReward", "administrator.challenge.error.EURsilverReward");

			isEURgoldReward = goldReward.getCurrency().equals("EUR") || goldReward.getCurrency().equals("€");
			errors.state(request, isEURgoldReward, "goldReward", "administrator.challenge.error.EURgoldReward");

			isSilverMoreThanBronze = silverReward.getAmount() > bronzeReward.getAmount();
			errors.state(request, isSilverMoreThanBronze, "silverReward", "administrator.challenge.error.silverMoreThanBronze");

			isGoldMoreThanSilver = goldReward.getAmount() > silverReward.getAmount();
			errors.state(request, isGoldMoreThanSilver, "goldReward", "administrator.challenge.error.goldMoreThanSilver");
		}

	}

	@Override
	public void create(final Request<Challenge> request, final Challenge entity) {
		this.repository.save(entity);

	}

}
