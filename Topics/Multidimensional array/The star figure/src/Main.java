import java.util.Arrays;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        int size = new Scanner(System.in).nextInt();
        String[][] matrix = new String[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                matrix[row][column] = isStar(size, row, column) ? "*" : ".";
            }
        }

        Arrays.stream(matrix).forEach(row -> {
            Arrays.stream(row).forEach(column -> System.out.print(column + " "));
            System.out.println();
        });
    }

    private static boolean isStar(int size, int row, int column) {
        boolean isLeftSideStarLocation = column == row;
        boolean isMiddleColumn = column == ((size + 1) / 2) - 1;
        boolean isMiddleRow = row == ((size + 1) / 2) - 1;
        boolean isRightSideStarLocation = column == size - row - 1;
        return isLeftSideStarLocation || isMiddleRow || isMiddleColumn || isRightSideStarLocation;
    }
}