import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ10026 {
	
	public static int N;
	public static char [][] graph;
	public static boolean [][] visited;
	public static int [] dy = {-1, 0, 1, 0};
	public static int [] dx = {0, 1, 0, -1};
	
	public static boolean checkBound(int ny, int nx) {
		return ny < 0 || ny >= N || nx < 0 || nx >= N;
	}
	
	public static void dfs(int y, int x, char color) {
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (checkBound(ny, nx)) continue;
			if (color != graph[ny][nx]) continue;
			if (visited[ny][nx]) continue;
			visited[ny][nx] = true;
			dfs(ny, nx, graph[ny][nx]);
		}
	}
	
	public static void dfs2(int y, int x, char color) {
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (checkBound(ny, nx)) continue;
			if (visited[ny][nx]) continue;
			if (color == 'R' || color == 'G') {
				if (graph[ny][nx] == 'R' || graph[ny][nx] == 'G') {
					visited[ny][nx] = true;
					dfs2(ny, nx, graph[ny][nx]);
				}
			} else {
				if (color == graph[ny][nx]) {
					visited[ny][nx] = true;
					dfs2(ny, nx, graph[ny][nx]);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new char [N][N];
		for (int i = 0; i < N; i++) {
			graph[i] = br.readLine().toCharArray();
		}
		visited = new boolean[N][N];
		int firstResult = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j]) continue;
				visited[i][j] = true;
				dfs(i, j, graph[i][j]);
				firstResult++;
			}
		}
		
		visited = new boolean[N][N];
		int secondResult = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j]) continue;
				visited[i][j] = true;
				dfs2(i, j, graph[i][j]);
				secondResult++;
			}
		}
		System.out.printf("%d %d", firstResult, secondResult);
	}

}
