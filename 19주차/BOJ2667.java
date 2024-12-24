import java.io.*;
import java.util.*;

public class BOJ2667 {
	
	static int N;
	static int[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visited = new boolean[N][N];
		
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = line.charAt(j);
			}
		}
		
		List<Integer> list = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j] == '1' && !visited[i][j]) {
					list.add(BFS(i, j));
				}
			}
		}
		
		Collections.sort(list);
		
		System.out.println(list.size());
		for (Integer cnt : list) {
			System.out.println(cnt);
		}
	}
	
	static int BFS(int i, int j) {
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {i, j});
		visited[i][j] = true;
		
		while (!queue.isEmpty()) {
			int x = queue.peek()[0];
			int y = queue.poll()[1];
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
					continue;
				}
				
				if (visited[nx][ny] || map[nx][ny] == '0') {
					continue;
				}
				
				queue.add(new int[] {nx, ny});
				visited[nx][ny] = true;
			}
			
			cnt++;
		}
		
		return cnt;
	}
}
