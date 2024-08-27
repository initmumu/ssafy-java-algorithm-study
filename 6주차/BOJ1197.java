import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1197 {
	
	public static int V, E;
	public static int[] parent; 
	public static int[][] graph;
	
	public static int find(int a) {
		if (parent[a] == a) return a;
		else return find(parent[a]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}
	
	public static void kruskal() {
		int cost = 0;
		for (int i = 0; i < graph.length; i++) {
			int a = find(graph[i][0]);
			int b = find(graph[i][1]);
			if (a != b) {
				cost += graph[i][2];
				union(graph[i][0], graph[i][1]);
			}
		}
		System.out.println(cost);
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		graph = new int[E][3];
		parent = new int[V+1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);
		
		kruskal();
		
	}

}
