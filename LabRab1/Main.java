package LabRab1;

import java.util.Scanner;

// Точка входа: ввод параметров, вызов Search.findArrangement, вывод результата.

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("N (монет гербами вверх): ");
        int N = scanner.nextInt();

        System.out.print("M (монет гербами вниз): ");
        int M = scanner.nextInt();

        System.out.print("S (переворачивается каждая S-я монета): ");
        int S = scanner.nextInt();

        System.out.print("K (количество ходов): ");
        int K = scanner.nextInt();

        System.out.print("L (сколько гербов вверх должно остаться): ");
        int L = scanner.nextInt();

        int total = N + M;

        // Валидация ввода
        if (N <= 0 || total <= 0 || S <= 0 || K < 0 || L < 0 || L > total) {
            System.out.println("Нет решения");
            return;
        }

        boolean[] best = Search.findArrangement(N, M, S, K, L);

        if (best == null) {
            System.out.println("Нет решения");
            return;
        }

        System.out.print("Начальная расстановка (Г — герб, Р — решка): ");
        for (boolean h : best) {
            System.out.print(h ? "Г " : "Р ");
        }
        System.out.println();
    }
}
