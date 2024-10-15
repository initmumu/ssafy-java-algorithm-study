import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static List<Integer>[] qs;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		qs = new ArrayList[N + 1];
		
		for(int i = 0; i < N + 1; i++) {
			qs[i] = new ArrayList<>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			qs[to].add(from);
		}
		
		List<Integer> result = new ArrayList<>();
		int max = 0;
		for(int i = 1; i <= N; i++) {
			if(qs[i].size() == 0) continue;
			
			visited = new boolean[N + 1];
			visited[i] = true;
			int cnt = 0;
			for(int n : qs[i]) {
				if(visited[n]) continue;
				visited[n] = true;
				cnt += DFS(n);
			}
			
			if(cnt > max) {
				result.clear();
				result.add(i);
				max = cnt;
			} else if(cnt == max) {
				result.add(i);
			}
		}
		
		result.sort((a, b) -> a - b);
		StringBuilder sb = new StringBuilder();
		for(int n : result) {
			sb.append(n).append(' ');
		}
		System.out.print(sb);
	}
	
	public static int DFS(int i) {
		if(qs[i] == null) return 0;
		int cnt = 1;
		for(int n : qs[i]) {
			if(visited[n]) continue;
			visited[n] = true;
			cnt += DFS(n);
		}
		
		return cnt;
	}
}