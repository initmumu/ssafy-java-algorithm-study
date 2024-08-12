import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1012 {
	
	public static int M, N, K;
	public static int [][] graph;
	public static boolean [][] visited;
	
	public static void dfs(int i, int j) {
		
		if (i <= -1 || i >= M || j <= -1 || j >= N) {
			return;
		}
		
		if (!visited[i][j] && graph[i][j] == 1) {
			visited[i][j] = true;
			dfs(i-1, j);
			dfs(i+1, j);
			dfs(i, j-1);
			dfs(i, j+1);
		}
	}
	

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int test_case = 0; test_case < T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			graph = new int [M][N];
			visited = new boolean [M][N];
			for (int k = 0; k < K; k++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				graph[a][b] = 1;
			}
			
			int count = 0;
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < N; j++) {
					if (graph[i][j] == 1 && !visited[i][j]) {
						dfs(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
		}

	}

}
