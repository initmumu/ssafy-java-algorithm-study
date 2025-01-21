import java.io.*;
import java.util.*;


public class BOJ3584 {
	
	public static StringBuilder answer = new StringBuilder();
	public static int[] reverseTree;
	public static HashSet<Integer> visited;
	
	public static void solution() throws IOException {
		int N = read();

		reverseTree = new int[N+1];
		visited = new HashSet<>();
		
		for (int i = 0; i < N - 1; i++) {
			int s = read();
			int e = read();
			reverseTree[e] = s;
		}
		
		int node1 = read(); int node2 = read();
		
		dfs(node1, 0);
		dfs(node2, 1);
	}
	
	public static void dfs(int start, int mode) {
		Stack<Integer> st = new Stack<>();
		st.add(start);
		if (mode == 0) visited.add(start);
		
		while(!st.isEmpty()) {
			int node = st.pop();
			
			if (mode == 1 && visited.contains(node)) {
				answer.append(node + "\n");
				return;
			}
			
			if (mode == 0 && reverseTree[node] != 0) {
				visited.add(reverseTree[node]);
			}
			
			if (reverseTree[node] != 0) st.add(reverseTree[node]);
		}
	}

	public static void main(String[] args) throws IOException {
		int TC = read();
		for (int tc = 0; tc < TC; tc++) solution();
		System.out.println(answer);

	}
	
	public static int read() throws IOException {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
		return t;
	}

}
