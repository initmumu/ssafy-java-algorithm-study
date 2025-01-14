import java.io.*;
import java.util.*;


public class BOJ1967 {
	
	static int ans = 0;
	
	public static class Edge {
		int dest, dist;
		Edge(int dest, int dist) {
			this.dest = dest;
			this.dist = dist;
		}
	}
	
	public static int dfs(ArrayDeque<Edge>[] tree, int node) {
		boolean[] visited = new boolean[tree.length];
		ArrayDeque<Edge> st = new ArrayDeque<>();
		st.push(new Edge(node, 0));
		
		
		while(!st.isEmpty()) {
			Edge e = st.pop();
			visited[e.dest] = true;
			
			int size = st.size();
			for (Edge e2: tree[e.dest]) {
				if (!visited[e2.dest]) {
					st.push(new Edge(e2.dest, e2.dist + e.dist));
				}
					
			}
			int size2 = st.size();
			if (size == size2) {
				if (ans < e.dist) {
					node = e.dest;
					ans = e.dist;
				}
				continue;
			}
			
			
		}
		return node;
	}

	public static void main(String[] args) throws IOException {
		int N = read();
		ArrayDeque<Edge>[] tree = new ArrayDeque[N+1];
		for (int i = 0; i <= N; i++) {
			tree[i] = new ArrayDeque<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			int s = read(); int e = read(); int d = read();
			tree[s].add(new Edge(e, d));
			tree[e].add(new Edge(s, d));
		}
		
		int node1 = dfs(tree, 1);
		dfs(tree, node1);
		System.out.println(ans);

	}
	
	public static int read() throws IOException {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
		if (c == 13) System.in.read();
		return t;
	}

}