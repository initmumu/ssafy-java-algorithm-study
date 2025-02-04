import java.io.*;

public class BOJ10830 {
    static int N;
    static int[][] matrix;
    static int MOD = 1000;

    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        N = in.readInt();
        long B = in.readLong();
        
        matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = in.readInt() % MOD;
            }
        }

        int[][] result = power(matrix, B);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static int[][] power(int[][] base, long exp) {
        if (exp == 1) return base;

        int[][] half = power(base, exp / 2);
        half = multiply(half, half);

        if (exp % 2 == 1) half = multiply(half, matrix);

        return half;
    }

    static int[][] multiply(int[][] A, int[][] B) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    result[i][j] = (result[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return result;
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

        public long readLong() throws IOException {
            long num = 0;
            int c;
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