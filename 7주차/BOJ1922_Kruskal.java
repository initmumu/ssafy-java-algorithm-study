import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1922_Kruskal {
	
	public static int N, M;
	public static int [][] graph;
	public static int parent[];
	
	public static void make() {
		parent = new int [N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	public static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		if (rootA == rootB) return false;
		
		if (rootA >= rootB) parent[rootB] = rootA;
		else parent[rootA] = rootB;
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		graph = new int [M][3];
		make();
		StringTokenizer st;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[i][0] = a;
			graph[i][1] = b;
			graph[i][2] = weight;
		}

		Arrays.sort(graph, (o1, o2) -> o1[2]-o2[2]);
		
		int cost = 0;
		int index = 0;
		for (int i = 0; i < M; i++) {
			if (union(graph[i][0], graph[i][1])) {
				index++;
				cost += graph[i][2];
				if (index == N-1) break;
			}
		}
		System.out.println(cost);
	}

}
