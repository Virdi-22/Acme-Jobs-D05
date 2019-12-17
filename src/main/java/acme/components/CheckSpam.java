
package acme.components;

import java.util.ArrayList;
import java.util.Collection;

import acme.entities.configurations.Configuration;

public class CheckSpam {

	public static boolean checkSpam(final Configuration config, final String text) {

		Collection<String> preparedSpam;
		Collection<String> preparedText;
		String spamWords;
		double spamThreshold;
		double calculatedSpam;
		double spamWordsInText = 0;
		double totalWords;
		boolean result;

		spamWords = config.getSpamWordsListing();
		spamThreshold = config.getSpamThreshold();
		preparedSpam = CheckSpam.prepareText(spamWords);
		preparedText = CheckSpam.prepareText(text);

		totalWords = preparedText.size();

		for (String w : preparedSpam) {
			for (String w2 : preparedText) {
				if (w.equals(w2) || w2.toLowerCase().contains(w.toLowerCase())) {
					spamWordsInText++;
				}
			}
		}

		calculatedSpam = Double.valueOf(spamWordsInText / totalWords) * 100;

		if (calculatedSpam >= spamThreshold) {
			result = true;
		} else {
			result = false;
		}

		return result;
	}

	public static Collection<String> prepareText(final String text) {

		Collection<String> result = new ArrayList<String>();

		String[] s = text.split(",");

		for (String word : s) {
			word.trim().replaceAll(" +", " ");
			result.add(word);
		}

		return result;
	}

}
