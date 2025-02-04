import java.io.*;

public class BOJ11660 {
    static int N, M;
    static int[][] prefixSum;

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        N = in.readInt();
        M = in.readInt();

        prefixSum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                int num = in.readInt();
                prefixSum[i][j] = num + prefixSum[i - 1][j] + prefixSum[i][j - 1] - prefixSum[i - 1][j - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int x1 = in.readInt();
            int y1 = in.readInt();
            int x2 = in.readInt();
            int y2 = in.readInt();

            int result = prefixSum[x2][y2] - prefixSum[x1 - 1][y2] - prefixSum[x2][y1 - 1] + prefixSum[x1 - 1][y1 - 1];
            sb.append(result).append("\n");
        }

        System.out.print(sb);
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