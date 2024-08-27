import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ4963 {
	
	static int w, h;
	static int[][] map;
	static boolean visited[][];
	static int count;
	
	static int[] dw = {0, 0 ,-1, 1, -1, -1, 1, 1};
	static int[] dh = {-1, 1, 0, 0, -1, 1, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		while (true) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			if (w == 0 && h == 0) break;
			
			map = new int[h+2][w+2];
			visited = new boolean[h+2][w+2];
			
			count = 0;
			
			for (int i = 1; i < h + 1; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j < w + 1; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for (int i = 1; i < h + 1; i++) {
				for (int j = 1; j < w + 1; j++) {
					if(map[i][j] == 1 && !visited[i][j]) {
						countIsland(i, j);
						count++;
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	static void countIsland(int w, int h) {
		int nw, nh;
		visited[w][h] = true;
		for (int d = 0; d < 8; d++) {
			nw = w + dw[d];
			nh = h + dh[d];
			
			if (!visited[nw][nh] && map[nw][nh] == 1) {
				countIsland(nw, nh);
			}
		}
	}
}
