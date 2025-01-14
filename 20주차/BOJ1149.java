import java.io.*;
import java.util.*;

public class BOJ1149 {
    public static void main(String[] args) throws IOException {
        int N = read();

        int[][] cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            cost[i][0] = read();
            cost[i][1] = read();
            cost[i][2] = read();
        }

        int[][] dp = new int[N][3];
        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        for (int i = 1; i < N; i++) {
            dp[i][0] = cost[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = cost[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = cost[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }

        int result = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));

        System.out.println(result);
    }
    
    public static int read() throws IOException {
    	int c, t = 0;
    	while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
    	return t;
    }
}
