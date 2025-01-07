package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {0, 1, 0, -1, -1, -1, 1, 1};
	static int[] dy = {1, 0, -1, 0, -1, 1, -1, 1};
	static int N, M;
	static int[][] arr;
	static boolean[][] visited;
	
	// 범위 안인지
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	// 산봉우리인지
	static boolean check(int a, int b) {
		
		// 산봉우리인지 여부
		boolean isOk = true;
		
		// bfs 위한 큐
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {a, b});
		visited[a][b] = true;
		
		// 산봉우리 검증 높이
		int target = arr[a][b];
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int x = temp[0];
			int y = temp[1];
			for (int d = 0; d < 8; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				if (isIn(nx, ny) && target < arr[nx][ny]) {
					isOk = false;
				}
				if (isIn(nx, ny) && !visited[nx][ny] && target == arr[nx][ny]) {
					queue.add(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
			}
		}
		
		return isOk;
	}
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 격자 크기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 격자
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 방문 여부 배열
		visited = new boolean[N][M];
		
		// 개수
		int cnt = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) {
					if (check(i, j)) {
						cnt++;
					}
				}
			}
		}
		
		System.out.println(cnt);
	}
}