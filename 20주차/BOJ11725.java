import java.io.*;
import java.util.*;

public class BOJ11725 {
	private static List<List<Integer>> graph;
	private static int[] parents;
	private static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		graph = new ArrayList<>();
		for (int i = 0; i <= n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int i = 0; i < n - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		parents = new int[n + 1];
		visited = new boolean[n + 1];

		dfs(1);

		StringBuilder sb = new StringBuilder();
		for (int i = 2; i <= n; i++) {
			sb.append(parents[i]).append("\n");
		}
		System.out.print(sb);
	}

	private static void dfs(int current) {
		visited[current] = true;

		for (int child : graph.get(current)) {
			if (!visited[child]) {
				parents[child] = current;
				dfs(child);
			}
		}
	}
}
