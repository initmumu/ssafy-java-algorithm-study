import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {
	
	public static int N, start_y, start_x, end_y, end_x;
	public static boolean [][] visited;
	public static int[] dy = {-2, -1, 1, 2, 2, 1, -1, -2};
	public static int[] dx = {1, 2, 2, 1, -1, -2, -2, -1};
	public static int MIN_NUM;
	
	public static void bfs(int y, int x, int level) {
		Queue<int[]> queue = new ArrayDeque<int[]>();
		visited[y][x] = true;
		queue.offer(new int[] {y, x, level});
		while (!queue.isEmpty()) {
			int [] numArr = queue.poll();
			y = numArr[0];
			x = numArr[1];
			level = numArr[2];
			if (y == end_y && x == end_x) {
				System.out.println(level);
				return;
			}
			
			for (int i = 0; i < 8; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if (ny < 0 || ny >= N || nx < 0 || nx >= N) continue;
				if (!visited[ny][nx]) {
					visited[ny][nx] = true;
					queue.offer(new int [] {ny, nx, level+1});
				}
			}
		}
	
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N][N];
			st = new StringTokenizer(br.readLine());
			start_y = Integer.parseInt(st.nextToken());
			start_x = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			end_y = Integer.parseInt(st.nextToken());
			end_x = Integer.parseInt(st.nextToken());
			MIN_NUM = Integer.MAX_VALUE;
			bfs(start_y, start_x, 0);
		}

	}

}
