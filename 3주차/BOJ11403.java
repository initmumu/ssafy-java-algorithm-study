import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11403 {
	public static int N;
	
	
	public static void dfs(int[][] graph, int s, int v, int[][] visited) {
		for (int i = 1; i <= N; i++) {
			if (graph[v][i] == 1 && visited[s][i] == 0) {
				visited[s][i] = 1;
				dfs(graph, s, i, visited);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		int[][] graph = new int[N+1][N+1];
		StringTokenizer st;
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int [][] visited = new int [N+1][N+1];
		for (int k = 1; k <= N; k++) {
			dfs(graph, k, k, visited);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sb.append(visited[i][j]+" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}
