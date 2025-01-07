package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		// dp[1] = 1
		// dp[2] = 2
		// dp[3] = 3
		// dp[4] = 5
		// dp[5] = 8
		
		long[] dp = new long[N + 1];
		dp[0] = 1;
		dp[1] = 1;
		
		if (N <= 1) {
			System.out.println(dp[1]);
		} else {
			for (int i = 2; i < N + 1; i++) {
				dp[i] = (dp[i - 2] + dp[i - 1])  % 15746;
			}
			System.out.println(dp[N]);
		}
	}
}