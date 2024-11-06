import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		int[] dp = new int[N + 1];
		
		if (N == 2) {
			dp[2] = 3;
		} else if (N >= 4) {
			dp[2] = 3;
			for (int i = 4; i <= N; i += 2) {
				dp[i] += dp[i - 2] * 3;
				int index = 4;
				while (true) {
					if (i - index >= 2) {
						dp[i] += dp[i - index] * 2;
						index += 2;
					} else {
						break;
					}
				}
				dp[i] += 2;
			}
		}
		System.out.println(dp[N]);
	}		
}

//N : 1 ANS : 0
//N : 2 ANS : 3
//N : 3 ANS : 0
//N : 4 ANS : 11 = 9 + 2 -> dp[2] * 3 + 2
//N : 5 ANS : 0
//N : 6 ANS : 41 = 33 + 6 + 2 -> dp[4] * 3 + dp[2] * 2 + 2
//N : 7 ANS : 0
//N : 8 ANS : 153 = 123 + 22 + 6 + 2 -> dp[6] * 3 + dp[4] * 2 + dp[2] * 2 + 2 
//N : 9 ANS : 0
//N : 10 ANS : 571 = 459 + 82 + 22 + 6 + 2 -> dp[8] * 3 + dp[6] * 2 + dp[4] * 2 + dp[2] * 2 + 2
//N : 30 ANS : 299303201