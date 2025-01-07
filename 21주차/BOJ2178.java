package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	static Queue<int[]> queue;
	static int[][] arr;
	static int N, M, cnt;
	
	// 배열 내부인지 확인
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	// 재귀로 짜는 bfs
	static void bfs() {
		int len = queue.size();
		
		if (len == 0) {
			return;
		}
		
		for (int i = 0; i < len; i++) {
			int[] temp = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = temp[0] + dx[d];
				int ny = temp[1] + dy[d];
				// 도착지점이면 바로 return
				if (nx == N - 1 && ny == M - 1) {
					cnt++;
					return;
				}
				// 범위 내에 있고, 방문 전이고, 이동 가능한 칸이면 고고링
				if (isIn(nx, ny) && arr[nx][ny] == 1) {
					int[] te = {nx, ny};
					queue.add(te);
					arr[nx][ny] = 0;
				}
			}
		}
		cnt++;
		bfs();
	}

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 행열 개수
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 배열
		arr = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				arr[i][j] = temp.charAt(j) - '0';
			}
		}
		
		// bfs 위한 큐
		queue = new ArrayDeque<int[]>();
		int[] temp = {0, 0};
		queue.add(temp);
		
		// 초기 위치 방문처리
		arr[0][0] = 0;
		
		// 정답 변수
		cnt = 1;
		
		bfs();
		
		System.out.println(cnt);
	}
}