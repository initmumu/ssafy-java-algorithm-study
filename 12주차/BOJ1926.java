import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1926 {
	
	public static int N, M;
	public static int [][] graph;
	public static int result = 0;
	public static int [] dy = {-1, 0, 1, 0};
	public static int [] dx = {0, 1, 0, -1};
	public static boolean [][] visited;
	
	public static boolean checkBound(int ny, int nx) {
		return ny < 0 || ny >= N || nx < 0 || nx >= M;
	}
	
	public static int dfs(int y, int x) {
		int area = 1;
		
		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if (checkBound(ny, nx)) continue;
			if (visited[ny][nx] || graph[ny][nx] == 0) continue;
			visited[ny][nx] = true;
			graph[ny][nx] = 0;
			area += dfs(ny, nx);
		}
		return area;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int [N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int len = 0;
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (graph[i][j] == 1) {
					visited[i][j] = true;
					graph[i][j] = 0;
					result = Math.max(result, dfs(i, j));
					len++;
				}
			}
		}
		System.out.println(len);
		System.out.println(result);

	}

}
