import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {

	public static int N, M;
	public static int r, c, d;
	public static int[][] graph;

	public static int[] dr = {-1, 0, 1, 0};
	public static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());

		graph = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(clean());
	}

	public static int clean() {
		boolean[][] visited = new boolean[N][M];
		int count = 0;

		while (true) {
			if (!visited[r][c]) {
				visited[r][c] = true;
				count++;
			}

			boolean cleaned = false;
			for (int i = 0; i < 4; i++) {
				d = (d + 3) % 4;
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && !visited[nr][nc] && graph[nr][nc] == 0) {
					r = nr;
					c = nc;
					cleaned = true;
					break;
				}
			}

			if (!cleaned) {
				int back = (d + 2) % 4;
				int nr = r + dr[back];
				int nc = c + dc[back];

				if (nr >= 0 && nr < N && nc >= 0 && nc < M && graph[nr][nc] == 0) {
					r = nr;
					c = nc;
				} else {
					break;
				}
			}
		}

		return count;
	}
}
