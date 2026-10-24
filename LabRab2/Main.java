package LabRab2;

/* Задание:
 * Дана разреженная матрица Ленточный столбцовый формат.
 * Обойти её по варианту 6 и вывести все ненулевые элементы.
*/


public class Main {

    public static void main(String[] args) {

        // Исходная разреженная матрица 5x5.
        // Храним её в ленточном столбцовом формате с полушириной ленты 1.
        int[][] full = {
                {1, 2, 0, 0, 0},
                {3, 4, 5, 0, 0},
                {0, 6, 7, 8, 0},
                {0, 0, 9, 1, 2},
                {0, 0, 0, 3, 4}
        };

        int lowerBand = 1;
        int upperBand = 1;

        BandedColumnMatrix matrix =
                new BandedColumnMatrix(full, lowerBand, upperBand);

        System.out.println("Исходная матрица:");
        matrix.printFullMatrix();

        System.out.println();
        Traversal.variant6(matrix);
    }
}
