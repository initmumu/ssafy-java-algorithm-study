import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4963 {
	
	public static int R, C;
	public static int graph [][];
	public static boolean visited [][];
	public static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0};
	public static int[] dx = {-1, 0, 1, 1, 1, 0, -1, -1};
	public static int result;
	
	public static void bfs(int y, int x) {
		Queue<int[]> queue = new ArrayDeque<>();
		visited[y][x] = true;
		queue.offer(new int [] {y, x});
		while (!queue.isEmpty()) {
			int [] numArr = queue.poll();
			y = numArr[0];
			x = numArr[1];
			for (int i = 0; i < 8; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || ny >= R || nx < 0 || nx >= C) continue;
				if (graph[ny][nx] == 1 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					queue.offer(new int[] {ny, nx});
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			C = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			if (C == 0 && R == 0) break;
			graph = new int [R][C];
			visited = new boolean [R][C];
			for (int i = 0; i < R; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < C; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			result = 0;
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (graph[i][j] == 1 && !visited[i][j]) {
						bfs(i, j);
						result++;
					}
				}
			}
			System.out.println(result);
		}
//		String temp = br.readLine();

	}

}
