package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N, M, ans;
	static int[][] box;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static Queue<int[]> queue;
	
	// 범위 안인지 여부
	static boolean isIn(int x, int y) {
		if (x >= 0 && y >= 0 && x < N && y < M) {
			return true;
		}
		return false;
	}
	
	// 토마토 익히기
	static void tomato() {
						
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				int c = temp[2];
				
				if (isIn(nx, ny) && box[nx][ny] == 0) {
					box[nx][ny] = 1;
					queue.add(new int[] {nx, ny, c + 1});
					ans = Integer.max(ans, c + 1);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 상자 크기
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		// 상자
		box = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				box[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 정답
		ans = 0;
		queue = new ArrayDeque<int[]>();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 1) {
					queue.add(new int[] {i, j, 0});
				}
			}
		}
		
		tomato();
		
		// 익지 않은 토마토 있으면 -1
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (box[i][j] == 0) {
					ans = -1;
				}
			}
		}
		
		System.out.println(ans);
	}
}	