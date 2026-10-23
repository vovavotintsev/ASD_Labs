package LabRab1;

import java.util.Scanner;

public class LabRab1 {

    // Узел кругового односвязного списка для монеты
    static class Coin {
        boolean head; // true — герб вверх, false — решка вверх
        Coin next;

        Coin(boolean head) {
            this.head = head;
            this.next = null;
        }
    }

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

        if (N <= 0 || total <= 0 || S <= 0 || K < 0 || L < 0 || L > total) {
            System.out.println("Нет решения");
            return;
        }

        boolean[] arrangement = new boolean[total];

        // Для устранения поворотов фиксируем первую монету гербом:
        // считаем, что первый счёт начинается именно с неё.
        arrangement[0] = true;

        boolean[] best = new boolean[total];

        boolean found = search(
                arrangement,
                1,
                N - 1,
                S,
                K,
                L,
                best
        );

        if (!found) {
            System.out.println("Нет решения");
            return;
        }

        System.out.print("Начальная расстановка (Г — герб, Р — решка): ");
        for (boolean h : best) {
            System.out.print(h ? "Г " : "Р ");
        }
        System.out.println();
    }


     // Перебор расстановок N гербов среди total позиций.
    static boolean search(boolean[] arrangement,
                          int pos,
                          int headsLeft,
                          int S,
                          int K,
                          int L,
                          boolean[] best) {

        if (pos == arrangement.length) {
            if (headsLeft == 0 && simulate(arrangement, S, K) == L) {
                System.arraycopy(arrangement, 0, best, 0, arrangement.length);
                return true;
            }
            return false;
        }

        int remaining = arrangement.length - pos;

        // Пробуем поставить герб
        if (headsLeft > 0) {
            arrangement[pos] = true;
            if (search(arrangement, pos + 1, headsLeft - 1, S, K, L, best)) {
                return true;
            }
        }

        // Пробуем поставить решку, если оставшихся позиций хватит для гербов
        if (remaining - 1 >= headsLeft) {
            arrangement[pos] = false;
            if (search(arrangement, pos + 1, headsLeft, S, K, L, best)) {
                return true;
            }
        }

        return false;
    }

     // Моделирование K ходов.
    static int simulate(boolean[] arrangement, int S, int K) {
        int n = arrangement.length;

        Coin first = null;
        Coin prev = null;

        for (boolean h : arrangement) {
            Coin coin = new Coin(h);
            if (first == null) {
                first = coin;
            } else {
                prev.next = coin;
            }
            prev = coin;
        }

        prev.next = first; // замыкаем круг

        Coin current = first;

        for (int move = 0; move < K; move++) {
            // Доходим до S-й монеты включительно
            for (int step = 1; step < S; step++) {
                current = current.next;
            }

            // Переворачиваем S-ю монету
            current.head = !current.head;

            // Следующий счёт начинается со следующей монеты
            current = current.next;
        }

        // Считаем гербы вверх
        int heads = 0;
        Coin p = first;
        for (int i = 0; i < n; i++) {
            if (p.head) {
                heads++;
            }
            p = p.next;
        }

        return heads;
    }
}