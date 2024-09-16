import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ6497 {
	
	public static int M, N;
	public static int parent[];
	public static int graph[][];
	
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) return false;
		if (a < b) parent[b]= a;
		else parent[a] = b;
		return true;
	}
	
	public static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static void make() {
		parent = new int [M];
		for (int i = 0; i < M; i++) {
			parent[i] = i;
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			if (M == 0 && N == 0) break;
			make();
			graph = new int[N][3];
			int sumAll = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				graph[i][0] = Integer.parseInt(st.nextToken());
				graph[i][1] = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				graph[i][2] = weight;
				sumAll += weight;
			}
			Arrays.sort(graph, (o1, o2) -> o1[2]-o2[2]);
			int cost = 0;
			int index = 0;
			for (int i = 0; i < N; i++) {
				if (union(graph[i][0], graph[i][1])) {
					cost += graph[i][2];
					index++;
					if (index == N-1) break;
				}
			}
			sb.append(sumAll-cost).append("\n");
		}
		System.out.println(sb);
	}

}
