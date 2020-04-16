package es.upm.etsisi.cf4j.qualityMeasure.recommendation;

import es.upm.etsisi.cf4j.qualityMeasure.QualityMeasure;
import es.upm.etsisi.cf4j.recommender.Recommender;
import es.upm.etsisi.cf4j.data.TestUser;
import es.upm.etsisi.cf4j.util.Search;

import java.util.Map;

/**
 * <p>This class calculates the Normalized Discounted Cumulative Gain (nDCG) of the recommendations performed by a
 * Recommender. It is calculated as follows:</p>
 * <p>NDCG = &lt;DCG&gt; / &lt;IDCG&gt;</p>
 */
public class NDCG extends QualityMeasure {

	/**
	 * Number of recommended items
	 */
	private int numberOfRecommendations;

	/**
	 * Constructor from a Map object with the quality measure parameters. Map object must contains the
	 * following keys:
	 * <ul>
	 *   <li><b>numberOfRecommendations</b>: int value with the number of items to be recommended.</li>
	 * </ul>
	 * @param recommender Recommender instance for which the NDCG are going to be computed
	 * @param params Quality measure's parameters
	 */
	public NDCG(Recommender recommender, Map<String, Object> params) {
		this(recommender, (int) params.get("numberOfRecommendations"));
	}

	/**
	 * Constructor
	 * @param recommender Recommender instance for which the nDCG are going to be computed
	 * @param numberOfRecommendations Number of recommendations
	 */
	public NDCG(Recommender recommender, int numberOfRecommendations) {
		super(recommender);
		this.numberOfRecommendations = numberOfRecommendations;
	}

	@Override
	protected double getScore(TestUser testUser, double[] predictions) {

		// Compute DCG

		int [] recommendations = Search.findTopN(predictions, this.numberOfRecommendations);

		double dcg = 0d;

		for (int pos = 0; pos < recommendations.length; pos++) {
			int i = recommendations[pos];
			if (i == -1) break;

			double rating = testUser.getTestRatingAt(i);
			dcg += (Math.pow(2, rating) - 1) / (Math.log(pos + 2) / Math.log(2));
		}

		// Compute IDCG

		double[] testRatings = new double[testUser.getNumberOfTestRatings()];
		for (int i = 0; i < testRatings.length; i++) {
			testRatings[i] = testUser.getRatingAt(i);
		}

		int [] idealRecommendations = Search.findTopN(testRatings, this.numberOfRecommendations);

		double idcg = 0d;

		for (int pos = 0; pos < idealRecommendations.length; pos++) {
			int i = idealRecommendations[pos];
			if (i == -1) break;

			double rating = testUser.getTestRatingAt(i);
			idcg += (Math.pow(2, rating) - 1) / (Math.log(pos + 2) / Math.log(2));
		}

		// Compute NDCG
		return dcg / idcg;
	}
}