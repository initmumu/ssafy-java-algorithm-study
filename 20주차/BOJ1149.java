package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 집 개수
		int N = Integer.parseInt(br.readLine());
		
		// 비용 정보
		int[][] costs = new int[N][3];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			costs[i][0] = r;
			costs[i][1] = g;
			costs[i][2] = b;
		}
		
		// 그 다음부턴 DP
		for (int i = 1; i < N; i++) {
			costs[i][0] += Integer.min(costs[i - 1][1], costs[i - 1][2]);
			costs[i][1] += Integer.min(costs[i - 1][0], costs[i - 1][2]);
			costs[i][2] += Integer.min(costs[i - 1][0], costs[i - 1][1]);
		}
		
		int ans = Integer.min(costs[N - 1][0], costs[N - 1][1]);
		ans = Integer.min(ans, costs[N - 1][2]);
		
		System.out.println(ans);
	}
}