import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int[][] matrix = new int[size][size];
        boolean isSymmetrical = true;

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                matrix[row][column] = scanner.nextInt();
            }
        }

        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                if (matrix[row][column] != matrix[column][row]) {
                    isSymmetrical = false;
                    break;
                }
            }
        }

        String result = isSymmetrical ? "YES" : "NO";
        System.out.println(result);

    }
}