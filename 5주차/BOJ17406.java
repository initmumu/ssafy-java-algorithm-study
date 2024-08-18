import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17406 {

	public static int N, M, K;
	public static int [][] graph;
	public static int [][] tempGraph;
	public static int [][] kGraph;
	public static boolean [] visited;
	public static int [] arr;
	public static int MIN_VALUE;
	
	public static void copyArray() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tempGraph[i][j] = graph[i][j];
			}
		}
	}
	
	public static void turnArray(int r, int c, int s) {

		for (int i = 1; i <= s; i++) {
			int [] dx = {-1, 0, 1, 0};
			int [] dy = {0, 1, 0, -1};
			int direction = 0;
			int cx = r-i;
			int cy = c-i;
			int temp = tempGraph[cx][cy];
			int temp2 = 0;
			tempGraph[cx][cy] = tempGraph[cx + dx[direction] * - 1][cy + dy[direction] * - 1];
			for (int j = 0; j < i*8-1; j++) {
				int nx = cx + dx[direction];
				int ny = cy + dy[direction];
				
				if (nx < r - i || nx > r + i || ny < c - i || ny > c + i) {
					direction = (direction + 1) % 4;
					nx = cx + dx[direction];
					ny = cy + dy[direction];
				}
				temp2 = tempGraph[nx][ny];
				
				tempGraph[nx][ny] = temp;
				
				temp = temp2;
				cx = nx;
				cy = ny;
			}
//			System.out.println(Arrays.deepToString(tempGraph));
		}
	}
	
	public static void dfs(int depth) {
		if (depth == K) {
			tempGraph = new int [N][M];
			copyArray();
			for (int index : arr) {
				int r = kGraph[index-1][0]-1;
				int c = kGraph[index-1][1]-1;
				int s = kGraph[index-1][2];
				
				turnArray(r, c, s);
			}
			
			for (int i = 0; i < N; i++) {
				int sum = 0;
				for (int j = 0; j < M; j++) {
					sum += tempGraph[i][j];
				}
				MIN_VALUE = Math.min(MIN_VALUE, sum);
			}
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			arr[depth] = i + 1;
			dfs(depth + 1);
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		graph = new int [N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		kGraph = new int [K][3];
		
		visited = new boolean [K];
		arr = new int [K];
		
		for (int k = 0; k < K; k++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			kGraph[k][0] = r;
			kGraph[k][1] = c;
			kGraph[k][2] = s;
		}
		MIN_VALUE = Integer.MAX_VALUE;
		dfs(0);
		System.out.println(MIN_VALUE);
	}

}
