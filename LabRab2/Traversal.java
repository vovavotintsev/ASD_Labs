package LabRab2;

public class Traversal {

    /**
     * Обход по варианту 6:
     * столбцы просматриваются слева направо,
     * чётные столбцы — снизу вверх,
     * нечётные столбцы — сверху вниз.
     */
    public static void variant6(BandedColumnMatrix matrix) {
        int n = matrix.getSize();

        System.out.println("Обход по варианту 6:");
        System.out.println("чётные столбцы — снизу вверх, нечётные — сверху вниз");

        for (int col = 0; col < n; col++) {
            if (col % 2 == 0) {
                for (int row = n - 1; row >= 0; row--) {
                    printIfNonZero(matrix, row, col);
                }
            } else {
                for (int row = 0; row < n; row++) {
                    printIfNonZero(matrix, row, col);
                }
            }
        }
    }

    private static void printIfNonZero(BandedColumnMatrix matrix, int row, int col) {
        int value = matrix.get(row, col);

        if (value != 0) {
            System.out.printf("a[%d][%d] = %d%n", row + 1, col + 1, value);
        }
    }
}