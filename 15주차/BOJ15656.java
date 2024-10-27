import java.io.*;
import java.util.*;

public class BOJ15656 {
	
	public static int N, M;
	public static int[] graph, num;
	public static StringBuilder sb;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		graph = new int[N];
		num = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(graph);
        
		sb = new StringBuilder();
        
		dfs(0);
		System.out.println(sb);
	}
	
	public static void dfs(int depth) {
		if (depth == M) {
            for (int n : num) {
                sb.append(n).append(" ");
            }
            sb.append("\n");
            return;
		}
		
		for (int i = 0; i < N; i++) {
			num[depth] = graph[i];
			dfs(depth + 1);
		}
	}
}
