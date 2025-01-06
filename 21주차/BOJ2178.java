import java.io.*;
import java.util.*;

public class BOJ2178 {
	
	static int N, M;
	static char[][] map;
	static boolean[][] visited;
	
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N + 1][M + 1];
		visited = new boolean[N + 1][M + 1];
		
		for (int i = 1; i <= N; i++) {
			String str = br.readLine();
			for (int j = 1; j <= M; j++) {
				map[i][j] = str.charAt(j-1);
			}
		}
		
		System.out.println(BFS());
		
	}
	
	static int BFS() {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {1, 1, 1});
		visited[1][1] = true;
		
		while(!queue.isEmpty()) {
			int[] pos = queue.poll();
			int x = pos[0];
			int y = pos[1];
			int distance = pos[2];
			
			if (x == N && y == M) {
				return distance;
			}
			
			for (int d = 0; d < 4; d++) {
				int nx = x + dx[d];
				int ny = y + dy[d];
				
				if (nx <= 0 || ny <= 0 || nx > N || ny > M) {
					continue;
				}
				
				if (visited[nx][ny] || map[nx][ny] == '0') {
					continue;
				}
				
				queue.add(new int[] {nx, ny, distance + 1});
				visited[nx][ny] = true;
			}
		}
		
		return 0;
	}
}
