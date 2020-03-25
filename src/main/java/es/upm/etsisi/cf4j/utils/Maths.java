package es.upm.etsisi.cf4j.utils;

/**
 * This class contains useful math methods.
 * @author Fernando Ortega
 */
public class Maths {
	 
	 /**
	  * Calculates the average of an double array
	  * @param array Array of double from which to calculate the mean
	  * @return Array mean
	  */
	 public static double arrayAverage(double[] array) {
		 double average = 0f;
		 for (double d : array) average += d;
		 return average / (double) array.length;
	 }

	/**
	 * Calculates the standard deviation of an double array
	 * @param array Array of double from which to calculate the standard deviation
	 * @return Standard deviation of the array
	 */
	public static double arrayStandardDeviation(double[] array) {
		double average = Maths.arrayAverage(array);
		double standard_deviation = 0f;
		for (double d : array) standard_deviation += (d - average) * (d - average);
		return Math.sqrt(standard_deviation / (double) array.length);
	}
	
	 /**
	  * Calculates the average of an int array
	  * @param array Array of int from which to calculate the mean
	  * @return Array mean
	  */
	 public static double arrayAverage(int[] array) {
		 double average = 0f;
		 for (int i : array) average += i;
		 return average / (double) array.length;
	 }

	/**
	 * Calculate the standard deviation of an int array
	 * @param array Array of int from which to calculate the standard deviation
	 * @return Standard deviation of the array
	 */
	public static double arrayStandardDeviation(int[] array) {
		double average = Maths.arrayAverage(array);
		double standard_deviation = 0f;
		for (int i : array) standard_deviation += (i - average) * (i - average);
		return Math.sqrt(standard_deviation / (double) array.length);
	}

	/**
	 * Dot product between two vectors
	 * @param a Vector A
	 * @param b Vector B
	 * @return dot_product(A, B)
	 */
	public static double dotProduct(double [] a, double [] b) {
		double r = 0;
		for (int i = 0; i < a.length; i++) r += a[i] * b[i];
		return r;
	}
}
