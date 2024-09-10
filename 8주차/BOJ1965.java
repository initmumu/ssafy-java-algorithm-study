import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1965 {
	
	public static int N;
	public static int [] graph, dp;
	
	public static int LIS() {
		int max = 1;
		for (int i = 0; i < N; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (graph[j] < graph[i] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j]+1;
					if (max < dp[i]) {
						max = dp[i];
					}
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int [N];
		dp = new int [N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}
		
		System.out.println(LIS());

	}

}
