import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ1260 {
	
	public static int N, M, V;
	public static StringBuilder sb = new StringBuilder();;
	
	public static void dfs(int[][] graph, int v, boolean[] visited) {
		
		visited[v] = true;
		sb.append(v+" ");
		for (int i = 1; i <= N; i++) {
			if (graph[v][i] == 1 && !visited[i]) {
				dfs(graph, i, visited);
			}
		}
	}
	
	public static void bfs(int[][] graph, int v, boolean[] visited) {
		Deque<Integer> deque = new ArrayDeque<Integer>();
		deque.add(v);
		visited[v] = true;
		while (!deque.isEmpty()) {
			v = deque.pollFirst();
			sb.append(v+" ");
			for (int i = 1; i <= N; i++) {
				if (graph[v][i] == 1 && !visited[i]) {
					visited[i] = true;
					deque.offerLast(i);
				}
			}
		}

	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		int[][] graph = new int[N+1][N+1];
		
		for (int i = 1; i <= M; i++) {
			StringTokenizer st2 = new StringTokenizer(br.readLine());
			int a =  Integer.parseInt(st2.nextToken());
			int b =  Integer.parseInt(st2.nextToken());
			graph[a][b] = graph[b][a] = 1;
		}
		boolean [] dfs_visited = new boolean [N+1];
		
		dfs(graph, V, dfs_visited);

		boolean [] bfs_visited = new boolean [N+1];
		
		sb.append("\n");
		bfs(graph, V, bfs_visited);
		
		System.out.println(sb);
	}

}
