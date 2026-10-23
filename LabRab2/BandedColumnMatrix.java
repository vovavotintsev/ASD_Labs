package LabRab2;

/**
 * Разреженная матрица в ленточном столбцовом формате.
 *
 * Для каждого столбца хранятся только элементы внутри ленты
 */
public class BandedColumnMatrix {

    private final int size;
    private final int lowerBand;
    private final int upperBand;

    private final int[][] columns;
    private final int[] rowStart;

    public BandedColumnMatrix(int[][] fullMatrix, int lowerBand, int upperBand) {
        this.size = fullMatrix.length;
        this.lowerBand = lowerBand;
        this.upperBand = upperBand;

        this.columns = new int[size][];
        this.rowStart = new int[size];

        for (int col = 0; col < size; col++) {
            int rs = Math.max(0, col - lowerBand);
            int re = Math.min(size - 1, col + upperBand);

            rowStart[col] = rs;
            columns[col] = new int[re - rs + 1];

            for (int row = rs; row <= re; row++) {
                columns[col][row - rs] = fullMatrix[row][col];
            }
        }
    }

    public int getSize() {
        return size;
    }

    public int get(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return 0;
        }

        int rs = rowStart[col];
        int re = rs + columns[col].length - 1;

        if (row < rs || row > re) {
            return 0;
        }

        return columns[col][row - rs];
    }

    public void printFullMatrix() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.printf("%3d ", get(row, col));
            }
            System.out.println();
        }
    }
}