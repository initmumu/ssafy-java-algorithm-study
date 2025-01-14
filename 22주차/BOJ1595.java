import java.util.*;
import java.io.*;


public class BOJ1595 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<Edge>[] adj = new ArrayList[10001];
	static int ans = -1, no = -1;
	
	static class Edge {
		int end;
		int dist;
		
		Edge(int end, int dist) {
			this.end = end;
			this.dist = dist;
		}
		
		public String toString() {
			return end + " " + dist;
		}
	}

	public static void dfs(int start) {
		ArrayDeque<Edge> stack = new ArrayDeque<>();
		boolean[] visited = new boolean[10001];
		
		stack.push(new Edge(start, 0));
		
		while(!stack.isEmpty()) {
			Edge node = stack.pop();
			visited[node.end] = true;
			
			int size = stack.size();
			for (Edge e: adj[node.end]) {
				if (visited[e.end]) continue;
				stack.push(new Edge(e.end, node.dist + e.dist));
			}
			int size2 = stack.size();
			
			if (size == size2) {
				if (ans < node.dist) {
					ans = node.dist;
					no = node.end;
				}
			}
		}
			
	}
	
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 10001; i++) {
			adj[i] = new ArrayList<>();
		}
		
		String input;
		while ((input = br.readLine()) != null && !input.isEmpty()) {
		    st = new StringTokenizer(input);
		    int start = Integer.parseInt(st.nextToken());
		    int end = Integer.parseInt(st.nextToken());
		    int dist = Integer.parseInt(st.nextToken());
		    
		    adj[start].add(new Edge(end, dist));
		    adj[end].add(new Edge(start, dist));
		}
		
		dfs(1);
		dfs(no);
		
		System.out.println(ans);

	}

}
