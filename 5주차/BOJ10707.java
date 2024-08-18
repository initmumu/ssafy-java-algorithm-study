import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ10707 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] board = new int[N+1][N+1];
        int[][][] dp = new int[N+1][N+1][3];
        // 해당 칸의 가로, 세로, 대각선 파이프의 개수를 저장하기 위한 dp 테이블

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][2][0] = 1;
        // 가장 처음에 파이프는 (1, 1)와 (1, 2)를 차지

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j] == 1) continue; // 현재 칸이 벽일 경우 continue

                // 해당 칸의 가로 파이프 개수 = 이전칸의 가로 파이프 개수 + 이전칸의 대각선 파이프 개수
                dp[i][j][0] += dp[i][j-1][0] + dp[i][j-1][2];

                // 해당 칸의 세로 파이프 개수 = 윗칸의 세로 파이프 개수 + 이전칸의 대각선 파이프 개수
                dp[i][j][1] += dp[i-1][j][1] + dp[i-1][j][2];

                // 해당 칸의 대각선 파이프 개수 = 이전윗칸의 모든 파이프 개수
                if (board[i-1][j] == 0 && board[i][j-1] == 0) {
                    dp[i][j][2] += dp[i-1][j-1][0] + dp[i-1][j-1][1] + dp[i-1][j-1][2];
                }
            }
        }

        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }
}
