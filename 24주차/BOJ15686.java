package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, cntChickens, cntHouses, ans;
	static boolean[] visited;
	static int[] selected;
	static ArrayList<int[]> chickens, houses;
	
	// 치킨집 M개 조합
	static void dfs(int depth, int start) {
		
		// 해당 조합의 도시 치킨 거리 구하고, 정답(최솟값) 갱신
		if (depth == M) {
			int selectedDist = 0;
			for (int i = 0; i < cntHouses; i++) {
				selectedDist += calc(i);
				if (selectedDist >= ans) {
					return;
				}
			}
			
			ans = Integer.min(ans, selectedDist);
			return;
		}
		
		for (int i = start; i < cntChickens; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[depth] = i;
				dfs(depth + 1, i + 1);
				visited[i] = false;
			}
		}
		
	}
	
	// 각 집의 치킨거리 구하는 함수
	static int calc(int houseNum) {
		int[] house = houses.get(houseNum);
		int minDist = Integer.MAX_VALUE;
		
		for (int i = 0; i < M; i++) {
			int[] chicken = chickens.get(selected[i]);
			int tempDist = (Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]));
			minDist = Integer.min(minDist, tempDist);
		}
		
		return minDist;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 도시 크기
		N = Integer.parseInt(st.nextToken());
		// 치킨집 개수
		M = Integer.parseInt(st.nextToken());
		
		// 치킨집들
		chickens = new ArrayList<int[]>();
		// 집들
		houses = new ArrayList<int[]>();
		
		// 도시 정보
		int[][] city = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int c = Integer.parseInt(st.nextToken());
				city[i][j] = c;
				if (c == 2) {
					chickens.add(new int[] {i, j});
				} else if (c == 1) {
					houses.add(new int[] {i, j});
				}
			}
		}
		
		// 치킨집 개수
		cntChickens = chickens.size();
		// 집 개수
		cntHouses = houses.size();
		
		// 순열조합 dfs 위해
		visited = new boolean[cntChickens];
		selected = new int[M];
		
		// 정답
		ans = Integer.MAX_VALUE;
		
		dfs(0, 0);
		
		System.out.println(ans);
	}
}