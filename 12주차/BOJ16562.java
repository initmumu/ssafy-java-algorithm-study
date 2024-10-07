import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ16562 {
	
	public static int N, M, K;
	public static int money[];
	public static int parent[];
	public static int minMoney[];
	
	public static int find(int a) {
		if (parent[a] == a) return a;
		return parent[a] = find(parent[a]);
	}
	
	public static void make() {
		parent = new int[N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}
	
	public static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if (a == b) return false;
		
		if (minMoney[a] < minMoney[b]) {
			parent[b] = a;
			minMoney[a] = Math.min(minMoney[a], minMoney[b]);
		}
		else {
			parent[a] = b;
			minMoney[b] = Math.min(minMoney[a], minMoney[b]);
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		money = new int[N+1];
		minMoney = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			int m = Integer.parseInt(st.nextToken());
			money[i] = m;
			minMoney[i] = m;
		}
		
		make();
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a, b);
		}
		
		for (int i = 1; i <= N; i++) {
			find(i);
		}
		
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i = 1; i <= N; i++) {
			set.add(parent[i]);
		}
		int sum = 0;
		for (int i : set) {
			sum += minMoney[i];
		}
		if (sum <= K) System.out.println(sum);
		else System.out.println("Oh no");

	}

}
