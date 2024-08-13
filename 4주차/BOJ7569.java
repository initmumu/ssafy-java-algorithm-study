import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ7569 {
	
	public static int M, N, H;
	public static int [][][] graph;
	public static Deque<int[]> deque;
	public static int[] dh = {0,0,0,0,1,-1};
	public static int[] dx = {1,0,0,-1,0,0};
	public static int[] dy = {0,1,-1,0,0,0};
	
	public static void bfs() {
		while(!deque.isEmpty()) {
			int [] poll = deque.pollFirst();
			int h = poll[0];
			int x = poll[1];
			int y = poll[2];
			for (int i = 0; i < 6; i++) {
				int nh = h + dh[i];
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nh > -1 && nh < H && nx > -1 && nx < N && ny > -1 && ny < M && graph[nh][nx][ny] == 0) {
					deque.addLast(new int[] {nh, nx, ny});
					graph[nh][nx][ny] = graph[h][x][y] + 1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		graph = new int[H][N][M];
		deque = new ArrayDeque<>();
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					graph[k][i][j] =  Integer.parseInt(st.nextToken());
					if (graph[k][i][j] == 1) {
						deque.addLast(new int[] {k, i, j});
					}
				}
			}
			
		}
		
		bfs();
		
		int count = 0;
		boolean flag = false;
		for (int k = 0; k < H; k++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (graph[k][i][j] == 0) {
						flag = true;
						break;
					}
					count = Math.max(count, graph[k][i][j]);
				}
			}
		}
		
		if (flag) {
			System.out.println(-1);
		} else {
			if (count == 1) {
				System.out.println(0);
			} else {
				System.out.println(count - 1);
			}
		}
		
	}

}
