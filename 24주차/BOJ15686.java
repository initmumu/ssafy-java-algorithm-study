import java.io.*;
import java.util.*;

public class BOJ15686 {
    static int N, M;
    static List<int[]> houses = new ArrayList<>();
    static List<int[]> chickens = new ArrayList<>();
    static int[][] city;
    static int minDistance = Integer.MAX_VALUE;
    static int[] selected;

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        N = in.readInt();
        M = in.readInt();
        
        city = new int[N][N];
        selected = new int[M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                city[i][j] = in.readInt();
                if (city[i][j] == 1) houses.add(new int[]{i, j});
                if (city[i][j] == 2) chickens.add(new int[]{i, j});
            }
        }

        selectChickens(0, 0);
        System.out.println(minDistance);
    }

    static void selectChickens(int depth, int start) {
        if (depth == M) {
            minDistance = Math.min(minDistance, calculateDistance());
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            selected[depth] = i;
            selectChickens(depth + 1, i + 1);
        }
    }

    static int calculateDistance() {
        int totalDist = 0;

        for (int[] house : houses) {
            int hx = house[0], hy = house[1];
            int minDist = Integer.MAX_VALUE;

            for (int i = 0; i < M; i++) {
                int[] chicken = chickens.get(selected[i]);
                int cx = chicken[0], cy = chicken[1];
                minDist = Math.min(minDist, Math.abs(hx - cx) + Math.abs(hy - cy));
            }
            totalDist += minDist;
        }
        return totalDist;
    }

    static class InputReader {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int cur, size;

        public InputReader(InputStream in) {
            this.in = in;
        }

        private int next() throws IOException {
            if (cur >= size) {
                cur = 0;
                size = in.read(buffer);
                if (size == -1) return -1;
            }
            return buffer[cur++];
        }

        public int readInt() throws IOException {
            int num = 0, c;
            boolean neg = false;

            while ((c = next()) <= ' ') ;
            if (c == '-') {
                neg = true;
                c = next();
            }
            do {
                num = num * 10 + (c - '0');
            } while ((c = next()) >= '0' && c <= '9');

            return neg ? -num : num;
        }
    }
}