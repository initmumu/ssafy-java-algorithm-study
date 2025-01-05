import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++){
                dp[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N - 1; i++){
            dp[i + 1][0] += Math.min(dp[i][1], dp[i][2]);
            dp[i + 1][1] += Math.min(dp[i][0], dp[i][2]);
            dp[i + 1][2] += Math.min(dp[i][0], dp[i][1]);
        }

        System.out.println(Math.min(dp[N - 1][0], Math.min(dp[N - 1][1], dp[N - 1][2])));
    }
}
