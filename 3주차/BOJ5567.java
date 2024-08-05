import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ5567 {
	
	public static int N, M, count;
    
	public static void bfs(int[][] graph, int v, boolean[] visited, int current) {
		Deque<Integer> deque = new ArrayDeque<Integer>();
		visited[v] = true;
		int[] level = new int[N + 1];
		level[v] = 1;
		deque.add(v);
		while(!deque.isEmpty()) {
			v = deque.pollFirst();
			int currentLevel = level[v];
			if (currentLevel > 3) {
				break;
			}
			for (int i = 1; i <= N; i++) {
				if (graph[v][i] == 1 && !visited[i]) {
					visited[i] = true;
					level[i] = currentLevel+1;
					deque.offerLast(i);
				}
			}
			count++;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		int [][] graph = new int [N+1][N+1];
		for (int i = 1; i <= M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a][b] = graph[b][a] = 1;
		}
		
		boolean[] visited = new boolean[N+1];
		bfs(graph, 1, visited, 1);
		System.out.println(count-1);
	}

}
