import java.io.*;
import java.util.*;

public class Main {
	
	static int n, m;
	static int[][] arr;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < n && y >= 0 && y < m) {
			return true;
		}
		return false;
	}
	
	static int bfs(int x, int y) {
		
		int cnt = 0;
		
		Queue<int[]> queue = new ArrayDeque<>();
		int[] t = {x, y};
		queue.add(t);
		
		while (!queue.isEmpty()) {
			int[] q = queue.poll();
			cnt++;
			arr[q[0]][q[1]] = 0;
			for (int d = 0; d < 4; d++) {
				int nx = q[0] + dx[d];
				int ny = q[1] + dy[d];
				
				if (isIn(nx, ny) && arr[nx][ny] == 1) {
					int[] temp = {nx, ny};
					queue.add(temp);
					arr[nx][ny] = 0;
				}
			}
		}
	return cnt;
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 도화지 세로 크기
		n = Integer.parseInt(st.nextToken());
		// 도화지 가로 크기
		m = Integer.parseInt(st.nextToken());
		
		// 도화지 정보
		arr = new int[n][m];
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int totalCnt = 0;
		int maxCnt = 0;
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (arr[i][j] == 1) {
					int tempCnt = bfs(i, j);
					if (tempCnt > 0) {
						totalCnt++;
						maxCnt = Integer.max(maxCnt, tempCnt);
					}
				}
			}
		}
		System.out.println(totalCnt);
		System.out.println(maxCnt);
	}
}