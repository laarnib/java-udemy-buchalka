import java.util.Scanner;

public class SortedArray {
    // write code here
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numberList;
        int size = 0;

        // Get size from the user.
        size = getArraySize(scanner);
        if (size == -1) {
            System.out.println("Invalid size.");
            System.out.println("Exiting...");
            System.exit(-1);
        }

        numberList = getIntegers(size);
        System.out.println("Unsorted: ");
        printArray(numberList);

        sortIntegers(numberList);

        System.out.println("");
        System.out.println("Sorted: ");
        printArray(numberList);
    }

    /* Get array size from the user.
       If size or user input is a non integer value, return -1.
     */
    public static int getArraySize(Scanner scanner) {
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
    don't store the input in the array.
     */
    public static int[] getIntegers(int arraySize) {
        Scanner scanner = new Scanner(System.in);
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

    // Sort list in descending order using Selection Sort
    public static int[] sortIntegers(int[] numberList) {
        boolean isSorted = false;
        int i = 0;
        int biggestNumber = 0;
        int biggestNumberIndex = 0;
        int temp = 0;

        // Repeat until no unsorted elements remain
        while (!isSorted) {
            biggestNumber = numberList[i];
            biggestNumberIndex = i;

            // Find the biggest number in the unsorted part of the array
            for (int j = i + 1; j < numberList.length; j++) {
                if (biggestNumber < numberList[j]) {
                    biggestNumber = numberList[j];
                    biggestNumberIndex = j;
                }
            }

            // Swap the biggest number found with the first element of the unsorted array
            temp = numberList[i];
            numberList[i] = biggestNumber;
            numberList[biggestNumberIndex] = temp;

            i++;

            // If element is the last on the list then everything is sorted
            if (i == numberList.length - 1) {
                isSorted = true;
            }
        }

        return numberList;
    }

    // Prints the contents of the array
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.println("Element " + i + " contents " + array[i]);
        }
    }
}
