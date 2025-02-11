import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] forest, dp;
    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0}; // 상하좌우 이동

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = readInt(br);
        forest = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                forest[i][j] = readInt(br);
            }
        }

        int maxSurvival = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxSurvival = Math.max(maxSurvival, dfs(i, j));
            }
        }

        System.out.println(maxSurvival);
    }

    static int dfs(int y, int x) {
        if (dp[y][x] != 0) return dp[y][x]; // 이미 계산된 경우
        dp[y][x] = 1; // 최소한 자기 자신은 방문

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d], nx = x + dx[d];
            if (ny >= 0 && ny < N && nx >= 0 && nx < N && forest[ny][nx] > forest[y][x]) {
                dp[y][x] = Math.max(dp[y][x], dfs(ny, nx) + 1);
            }
        }

        return dp[y][x];
    }

    static int readInt(BufferedReader br) throws IOException {
        int val = 0;
        boolean negative = false;
        int c;

        while ((c = br.read()) != -1) {
            if (c == '-') {
                negative = true;
            } else if (c >= '0' && c <= '9') {
                val = val * 10 + (c - '0');
            } else if (c == ' ' || c == '\n') {
                break;
            }
        }

        return negative ? -val : val;
    }
}
