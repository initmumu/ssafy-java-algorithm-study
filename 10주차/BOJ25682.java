import java.io.*;
import java.util.*;

public class BOJ25682 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];

        for (int i = 0; i < N; i++) {
            board[i] = br.readLine().toCharArray();
        }


        int[][] repaintB = new int[N + 1][M + 1];   // B로 시작하는 경우 누적합
        int[][] repaintW = new int[N + 1][M + 1];   // W로 시작하는 경우 누적합

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                int addB = 0, addW = 0;

                if ((i + j) % 2 == 0) {
                    addB = (board[i - 1][j - 1] == 'B') ? 0 : 1;
                    addW = (board[i - 1][j - 1] == 'W') ? 0 : 1;
                } else {
                    addB = (board[i - 1][j - 1] == 'B') ? 1 : 0;
                    addW = (board[i - 1][j - 1] == 'W') ? 1 : 0;
                }

                repaintB[i][j] = repaintB[i - 1][j] + repaintB[i][j - 1] - repaintB[i - 1][j - 1] + addB;
                repaintW[i][j] = repaintW[i - 1][j] + repaintW[i][j - 1] - repaintW[i - 1][j - 1] + addW;
            }
        }

        int min = Integer.MAX_VALUE;

        for (int i = K; i <= N; i++) {
            for (int j = K; j <= M; j++) {
                int cntB = repaintB[i][j] - repaintB[i - K][j] - repaintB[i][j - K] + repaintB[i - K][j - K];
                int cntW = repaintW[i][j] - repaintW[i - K][j] - repaintW[i][j - K] + repaintW[i - K][j - K];

                min = Math.min(min, Math.min(cntB, cntW));
            }
        }

        System.out.println(min);
    }
}
