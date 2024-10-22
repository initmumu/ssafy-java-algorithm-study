import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1043 {
	
	public static int N, M;
	public static int parent [];
	public static List<List<Integer>> graph;
	
	public static void makeSet() {
		parent = new int [N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	public static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) return;
		
		if (a < b) parent[b] = a;
		else parent[a] = b;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int trueN = Integer.parseInt(st.nextToken());
		int [] true_arr = new int [trueN];
		if (trueN > 0) {
			for (int i = 0; i < trueN; i++) {
				true_arr[i] = Integer.parseInt(st.nextToken());
			}
		}
		
		makeSet();
		
		graph = new ArrayList<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int len = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			graph.add(new ArrayList<>());
			graph.get(i).add(a);
			if (len < 2) continue;
			for (int j = 0; j < len-1; j++) {
				int b = Integer.parseInt(st.nextToken());
				graph.get(i).add(b);
				union(a, b);
			}
		}
		
		for (int i = 1; i <= N; i++) {
			find(parent[i]);
		}
		int result = 0;
		for (List<Integer> arr: graph) {
			boolean flag = false;
			for (int a: arr) {
				for (int i = 0; i < true_arr.length; i++) {
					if (find(parent[a]) == find(parent[true_arr[i]])) {
						flag = true;
						break;
					}
				}
				if (flag) break;
			}
			if (!flag) result++;
		}
		
		System.out.println(result);

	}

}
