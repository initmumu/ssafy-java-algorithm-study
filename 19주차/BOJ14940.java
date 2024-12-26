import java.io.*;
import java.util.*;

public class Main {
	
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int n, m;
	
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < n && y >= 0 && y < m) {
			return true;
		}
		return false;
	}
		
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 지도 세로 크기
		n = Integer.parseInt(st.nextToken());
		// 지도 가로 크기
		m = Integer.parseInt(st.nextToken());
		
		// 목표지점 좌표
		int x = -1;
		int y = -1;
		
		// 지도 정보
		int[][] map = new int[n][m];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 2) {
					x = i;
					y = j;
				}
			}
		}
		
		// 방문 배열
		boolean[][] visited = new boolean[n][m];
		visited[x][y] = true;
		map[x][y] = 0;
		
		// bfs에 쓸 queue
		Queue<int[]> queue = new ArrayDeque<int[]>();
		queue.add(new int[] {x, y});
		
		// bfs 돌리기
		while (!queue.isEmpty()) {
			int[] next = queue.poll();
			
			for (int d = 0; d < 4; d++) {
				int nx = next[0] + dx[d];
				int ny = next[1] + dy[d];
				
				if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] != 0) {
					visited[nx][ny] = true;
					map[nx][ny] = map[next[0]][next[1]] + 1;
					queue.add(new int[] {nx, ny});
				}
			}
		}
		
		// 정답 출력
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (!visited[i][j] && map[i][j] != 0) {
					System.out.print("-1" + " ");
				} else {
					System.out.print(map[i][j] + " ");
				}
			}
			System.out.println();
		}
	}
}