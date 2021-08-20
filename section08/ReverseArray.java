import java.util.Arrays;
import java.util.Scanner;

public class ReverseArray {
    private static Scanner scanner = new Scanner(System.in);
    private static final String TEXT_RESET = "\u001B[0m";
    private static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_PURPLE = "\u001B[35m";

    public static void main(String[] args) {

		/* Create an array. Prompt user for array size
		and elements that are going to be stored in the array */
        int[] numberList = readElements(readInteger());

        // CLose the scanner
        scanner.close();

        // Reverse the array
        reverse(numberList);
    }

    private static int readInteger() {
        int readInteger = 0;

        // Keep prompting until the user enters a valid integer value
        while(true) {
            System.out.print("How many elements do you want to enter? ");

            // Catch NumberFormatException when user enters a non integer value
            try {
                readInteger = Integer.valueOf(scanner.nextLine());

                /* Check that the integer value entered is greater than zero
                and if it is, exit the loop and return the readInteger */
                if (readInteger <= 0) {
                    System.out.println(TEXT_RED + "Invalid value. Please enter an integer number greater than zero." + TEXT_RESET);
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(TEXT_RED + "That is not an integer. Please enter an integer value greater than zero." + TEXT_RESET);
            }
        }

        return readInteger;
    }

    private static int[] readElements(int size) {
        int[] list = new int[size];

        int i = 0;
        while (i < size) {
            System.out.print("Enter a number: ");
            try {
                list[i] = Integer.valueOf(scanner.nextLine());
                i++;
            } catch (NumberFormatException e) {
                System.out.println(TEXT_RED + "That is not an integer. Please enter an integer value." + TEXT_RESET);
            }
        }

        return list;
    }

    // Reverse the array
    private static void reverse(int[] list) {
        System.out.println("");
        System.out.println("Array = " + Arrays.toString(list));

        for (int i = 0, j = list.length - 1; i < list.length / 2; i++, j--) {
            int temp = list[i];
            list[i] = list[j];
            list[j] = temp;
        }

        System.out.println(TEXT_PURPLE + "Reversed Array = " + Arrays.toString(list) + TEXT_RESET);
    }
}