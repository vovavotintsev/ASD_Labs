package LabRab1;

 // перебор расстановок монет и моделирование K ходов.
 
public class Search {

    public static boolean[] findArrangement(int N, int M, int S, int K, int L) {

        int total = N + M;

        boolean[] arrangement = new boolean[total];
        // Фиксируем первую монету гербом — устраняем симметрию поворота
        arrangement[0] = true;

        boolean[] best = new boolean[total];

        boolean found = search(arrangement, 1, N - 1, S, K, L, best);

        return found ? best : null;
    }

    private static boolean search(boolean[] arrangement, int pos, int headsLeft,
                                  int S, int K, int L, boolean[] best) {

        if (pos == arrangement.length) {
            if (headsLeft == 0 && simulate(arrangement, S, K) == L) {
                System.arraycopy(arrangement, 0, best, 0, arrangement.length);
                return true;
            }
            return false;
        }

        int remaining = arrangement.length - pos;

        if (headsLeft > 0) {
            arrangement[pos] = true;
            if (search(arrangement, pos + 1, headsLeft - 1, S, K, L, best)) {
                return true;
            }
        }

        if (remaining - 1 >= headsLeft) {
            arrangement[pos] = false;
            if (search(arrangement, pos + 1, headsLeft, S, K, L, best)) {
                return true;
            }
        }

        return false;
    }

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
        prev.next = first;

        Coin current = first;

        for (int move = 0; move < K; move++) {
            for (int step = 1; step < S; step++) {
                current = current.next;
            }
            current.head = !current.head;
            current = current.next;
        }

        int heads = 0;
        Coin p = first;
        for (int i = 0; i < n; i++) {
            if (p.head) heads++;
            p = p.next;
        }
        return heads;
    }
}
