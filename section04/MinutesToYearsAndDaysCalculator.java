public class MinutesToYearsAndDaysCalculator {
	public static void main(String[] args) {
		printYearsAndDays(525600);
		printYearsAndDays(1051200);
		printYearsAndDays(561600);
	}

	public static void printYearsAndDays(long minutes) {
		if (minutes < 0) {
			System.out.println("Invalid value");
		}
		
		System.out.println(minutes + " min = " + convertMinutesToYearsAndDays(minutes));
	}

	public static String convertMinutesToYearsAndDays(long minutes) {
		long days = (minutes / 60) / 24;
		long years = days / 365;
		long remainingDays = days % 365;

		return years + " y and " + remainingDays + " d";
	}
}       
