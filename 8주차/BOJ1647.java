import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1647 {
	
	public static int N, M;
	public static int [][] graph;
	public static int [] parent;
	
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
		a = find(a);
		b = find(b);
		
		if (a == b) return false;
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken()); 
		M = Integer.parseInt(st.nextToken()); 
		graph = new int [M][3];
		make();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			graph[i][0] = Integer.parseInt(st.nextToken());
			graph[i][1] = Integer.parseInt(st.nextToken());
			graph[i][2] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(graph, (o1, o2) -> o1[2] - o2[2]);
		
		int cost = 0;
		int num = 0;
		for (int i = 0; i < M; i++) {
			if (union(graph[i][0], graph[i][1])) {
				if (num == N-2) break;
				cost += graph[i][2];
				num++;
			}
		}
		System.out.println(cost);
	}

}
