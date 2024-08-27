import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1717 {
	
	public static int N, M;
	public static int [] parent;
	
	public static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}
	
	public static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a != b) {
			if (a < b) {
				parent[b] = a;
			} else {
				parent[a] = b;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parent = new int [N+1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int command = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (command == 0) {
				union(a, b);
			} else {
				a = find(a);
				b = find(b);
				if (a==b) {
					System.out.println("YES");
				} else {
					System.out.println("NO");
				}
			}
		}


	}

}
