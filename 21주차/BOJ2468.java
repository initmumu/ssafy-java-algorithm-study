package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, -1, 0, 1};
	static int[] dy = {-1, 0, 1, 0};
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	
	// 범위 안인지 여부
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}
	
	// 섬 개수 1번 셈
	static void areaCalc(int a, int b, int height) {
	
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {a, b});
		visited[a][b] = true;
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (isIn(nx, ny) && !visited[nx][ny] && arr[nx][ny] > height) { 
					queue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 배열 크기
		N = Integer.parseInt(br.readLine());
		
		// 영역
		arr = new int[N][N];
		
		// 영역 중 최대 높이
		int maxHeight = 0;
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Integer.max(maxHeight, arr[i][j]);
			}
		}
		
		// 정답 (영역 개수)
		int ans = 1;
		
		// 방문 배열
		
		for (int i = 1; i < maxHeight; i++) {
			visited = new boolean[N][N];
			int tempAns = 0;
			for (int x = 0; x < N; x++) {
				for (int y = 0; y < N; y++) {
					if (isIn(x, y) && !visited[x][y] && arr[x][y] > i) {
						areaCalc(x, y, i);
						tempAns++;
					}
				}
			}
			ans = Integer.max(ans, tempAns);
		}
		
		System.out.println(ans);
	}
}