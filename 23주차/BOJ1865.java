package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 테스트케이스 개수
		int TC = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < TC; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 지점의 수
			int N = Integer.parseInt(st.nextToken());
			// 도로의 개수
			int M = Integer.parseInt(st.nextToken());
			// 웜홀의 개수
			int W = Integer.parseInt(st.nextToken());
			
			// 플로이드-워샬 위한 그래프 초기화
			int[][] graph = new int[N + 1][N + 1];
			int INF = 99999999;
			
			for (int i = 0; i < N + 1; i++) {
				for (int j = 0; j < N + 1; j++) {
					if (i != j) {
						graph[i][j] = INF;
					}
				}
			}
		
			// 도로 (양방향)
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
								
				graph[S][E] = Integer.min(graph[S][E], T);
				graph[E][S] = Integer.min(graph[E][S], T);
			}
			
			// 웜홀 (단방향)
			for (int i = 2 * M; i < 2 * M + W; i++) {
				st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int E = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
								
				graph[S][E] = Integer.min(graph[S][E], T * -1);
			}
			
			// 플로이드-워샬
			for (int i = 1; i < N + 1; i++) {
				for (int k = 1; k < N + 1; k++) {
					for (int j = 1; j < N + 1; j++) {
						graph[i][j] = Integer.min(graph[i][j], graph[i][k] + graph[k][j]);
					}
				}
			}
			
			String ans = "NO";
			for (int i = 1; i < N + 1; i++) {
				if (graph[i][i] < 0) {
					ans = "YES";
				}
			}
			
			System.out.println(ans);
		}
	}
}