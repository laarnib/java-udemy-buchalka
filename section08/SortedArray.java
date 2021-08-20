import java.util.Arrays;
import java.util.Scanner;

public class SortedArray {
    // write code here
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[] numberList;
        int size = 0;

        /* Get size from the user and if size is a -1, print Invalid size
        to screen and terminate the program. */
        size = getArraySize();
        if (size == -1) {
            System.out.println("Invalid size.");
            System.out.println("Exiting...");
            System.exit(-1);
        }

        numberList = getIntegers(size);

        // Close the scanner
        scanner.close();

        // Print unsorted array
        System.out.println("Unsorted: ");
        printArray(numberList);

        /* Make a copy of the array and sort the copy in descending order.
        Print the sorted array */
        System.out.println("");
        System.out.println("Sorted: ");
        printArray(sortIntegers(numberList));
    }

    /* Get array size from the user.
       If size or user input is a non integer value, return -1.*/
    public static int getArraySize() {
        int size = 0;

        System.out.print("How many numbers do you want to enter? ");
        if (scanner.hasNextInt()) {
            size = scanner.nextInt();
            if (size <= 0) {
                return -1;
            }
            return size;
        } else {
            scanner.nextLine();
            return -1;
        }
    }

    /* Get integers from the user. If user enters a non integer value,
    don't store the input in the array.*/
    public static int[] getIntegers(int arraySize) {
        int[] numberList = new int[arraySize];

        System.out.println("Enter your numbers below: ");
        for (int i = 0; i < arraySize; ) {
            if (scanner.hasNextInt()) {
                numberList[i] = scanner.nextInt();
                i++;
            } else {
                scanner.nextLine();
            }
        }

        return numberList;
    }

    // Make a copy of the array and sort list in descending order using Selection Sort
    public static int[] sortIntegers(int[] numberList) {
        // Make a copy of the array to leave the original array unchanged
        int[] sortedList = Arrays.copyOf(numberList, numberList.length);
        int biggestNumber, biggestNumberIndex, temp;

        for (int i = 0; i < sortedList.length - 1; i++) {
            biggestNumber = sortedList[i];
            biggestNumberIndex = i;
            for (int j = i + 1; j < sortedList.length; j++) {
                if (sortedList[j] > biggestNumber) {
                    biggestNumber = sortedList[j];
                    biggestNumberIndex = j;
                }
            }

            temp = sortedList[i];
            sortedList[i] = biggestNumber;
            sortedList[biggestNumberIndex] = temp;
        }

        return sortedList;
    }

    // Prints the contents of the array
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + " contents " + array[i]);
        }
    }
}