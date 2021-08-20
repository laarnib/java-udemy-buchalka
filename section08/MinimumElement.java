import java.util.Scanner;

public class MinimumElement {
    private static Scanner scanner = new Scanner(System.in);
    private static final String TEXT_RESET = "\u001B[0m";
    private static final String TEXT_RED = "\u001B[31m";
    public static final String TEXT_PURPLE = "\u001B[35m";

    public static void main(String[] args) {
        int[] numberList = readElements(readInteger());

        System.out.println("");
        System.out.println("The smallest number is: " + TEXT_PURPLE + findMin(numberList) + TEXT_RESET);
    }

    private static int readInteger() {
        int readInteger = 0;
        //boolean repeatLoop = true;

        // Keep prompting until the user enters a valid integer value
        while(true) {
            System.out.print("How many elements do you want to enter? ");

            // Catch NumberFormatException when user enters a non integer value
            try {
                readInteger = Integer.valueOf(scanner.nextLine());

                /* Check that the integer value entered is greater than zero
                and if it is, exit the loop and return the readInteger */
                if (readInteger <= 0) {
                    //repeatLoop = true;
                    System.out.println(TEXT_RED + "Invalid value. Please enter an integer number greater than zero." + TEXT_RESET);
                } else {
                    //repeatLoop = false;
                    break;
                }
            } catch (NumberFormatException e) {
                //repeatLoop = true;
                System.out.println(TEXT_RED + "That is not an integer. Please enter an integer value greater than zero." + TEXT_RESET);
            }
        }

        return readInteger;
    }

    private static int[] readElements(int size) {
        int[] list = new int[size];

        int i = 0;
        while (i < size) {
            try {
                list[i] = Integer.valueOf(scanner.nextLine());
                i++;
            } catch (NumberFormatException e) {
                System.out.println(TEXT_RED + "That is not an integer. Please enter an integer value." + TEXT_RESET);
            }
        }

        return list;
    }

    // Finds the minimum value in an integer array
    private static int findMin(int[] list) {
        int min = list[0];

        // Using linear search because the list is not sorted.
        for (int i = 1; i < list.length; i++) {
            if (list[i] < min) {
                min = list[i];
            }
        }

        return min;
    }
}
