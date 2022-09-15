import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int matrixSize = Integer.parseInt(scanner.nextLine());
        int[][] matrix = new int[matrixSize][];
        for (int i = 0; i < matrixSize; i++) {
            matrix[i] = getRow(matrixSize, i);
        }

        for (int[] row : matrix) {
            printRow(row);
        }
    }

    public static int[] getRow(int numCol, int startIndex) {
        int[] row = new int[numCol];
        int value = 0;
        // Populate right side
        for (int i = startIndex; i < numCol; i++) {
            row[i] = value;
            value++;
        }

        // Reset value
        value = 1;

        //Populate left side
        for (int i = startIndex - 1; i >= 0; i--) {
            row[i] = value;
            value++;
        }

        return row;
    }

    public static void printRow(int[] row) {
        Arrays.stream(row).forEach(num -> System.out.print(num + " "));
        System.out.println();
    }
}