import java.io.*;
import java.util.*;

public class BOJ1389 {

	public static void main(String[] args) throws IOException {
		int N = read();
		int M = read();
		
		int[][] dp = new int[N+1][N+1];
		for (int i = 0; i <= N; i++) Arrays.fill(dp[i], 100000);
		
		for (int i = 0; i < M; i++) {
			int a = read();
			int b = read();
			dp[a][b] = 1;
			dp[b][a] = 1;
		}
		
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
			}
		}
		
		int min = Integer.MAX_VALUE;
		int num = -1;
		
		for (int i = 1; i <= N; i++) {
			int sum = 0;
			for (int j = 1; j <= N; j++) {
				
				sum += dp[i][j];
			}
			if (min > sum) {
				num = i;
				min = sum;
			}
		}
		
		System.out.println(num);
	}
	
	public static int read() throws IOException {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
		return t;
	}

}