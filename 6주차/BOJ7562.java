import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562 {
	
	static int l;
	static int x, y;
	static boolean[][] visited;
	
	static int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
	static int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
	
	static int count;
	
	static Queue<int[]> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			l = Integer.parseInt(br.readLine());
			visited = new boolean[l][l];
			
			count = 0;
			
			st = new StringTokenizer(br.readLine());
			queue = new LinkedList<>();
			queue.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0});
			visited[queue.peek()[0]][queue.peek()[1]] = true;
			
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			
			System.out.println(move());
		}
		
	}
	
	
	static int move() {
		while(!queue.isEmpty()) {
			int[] pos = queue.poll();
				
			if (pos[0] == x && pos[1] == y) return pos[2];

			for (int d = 0; d < 8; d++) {
				int nx = pos[0] + dx[d];
				int ny = pos[1] + dy[d];
				int cnt = pos[2];
					
				if (nx < 0 || nx >= l || ny < 0 || ny >= l || visited[nx][ny]) continue;
				queue.add(new int[] {nx, ny, cnt + 1});
				visited[nx][ny] = true;
			}
			
		}
		return 0;
	}
}
