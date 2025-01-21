import java.io.*;
import java.util.*;

public class BOJ19535 {
    static final int MAX_N = 300000;

    public static void main(String[] args) throws Exception {
        int N = read();
        List<int[]> edges = new ArrayList<>();
        int[] degree = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            int u = read();
            int v = read();
            edges.add(new int[]{u, v});
            degree[u]++;
            degree[v]++;
        }

        long pathCount = 0;
        long starCount = 0;

        for (int i = 1; i <= N; i++) {
            if (degree[i] >= 3) {
                starCount += combination(degree[i], 3);
            }
        }

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            pathCount += (long) (degree[u] - 1) * (degree[v] - 1);
        }

        if (starCount * 3 < pathCount) {
            System.out.println("D");
        } else if (starCount * 3 > pathCount) {
            System.out.println("G");
        } else {
            System.out.println("DUDUDUNGA");
        }
    }

    public static int read() throws Exception {
        int c, number = 0;
        while ((c = System.in.read()) > 32) {
            number = (number << 3) + (number << 1) + (c & 15);
        }
        return number;
    }

    public static long combination(int n, int r) {
        if (n < r) return 0;
        if (r == 3) return (long) n * (n - 1) * (n - 2) / 6;
        return 0;
    }
}