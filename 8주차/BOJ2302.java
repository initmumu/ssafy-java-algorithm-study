import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2302 {
	
	public static int N, M;
	public static int [] vips, dp;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int [N+1];
		M = Integer.parseInt(br.readLine());
		vips = new int [M];
		for (int i = 0; i < M; i++) {
			int a = Integer.parseInt(br.readLine());
			vips[i] = a;
		}
		dp[0] = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = dp[i-1] + dp[i-2];
		}
		
		int result = 1;
		int lastVip = 0;
		for (int i = 0; i < M; i++) {
			int vip = vips[i];
			int gap = vip-lastVip-1;
			result *= dp[gap];
			lastVip = vip;
		}
		
		int gap = N - lastVip;
		result *= dp[gap];
		System.out.println(result);
	}

}
