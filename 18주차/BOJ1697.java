import java.io.*;
import java.util.*;

public class BOJ1697 {
	
	static int N, K;
	static boolean[] visited;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[100_001];
		
		System.out.println(BFS());
	}
	
	static int BFS() {
		Queue<int[]> queue = new LinkedList<>();
		visited[N] = true;
		queue.add(new int[] {N, 0});
		
		while (!queue.isEmpty()) {
			int pos = queue.peek()[0];
			int cnt = queue.poll()[1];
			
			if (pos == K) {
				return cnt;
			}
			
			if (pos - 1 == K || pos + 1 == K || 2 * pos == K) {
				return cnt + 1;
			}

			if (0 <= pos + 1 && pos + 1 < 100_001 && !visited[pos + 1]) {
				queue.add(new int[] {pos + 1, cnt + 1});
				visited[pos + 1] = true;
			}
			
			if (0 <= pos - 1 && pos - 1 < 100_001 && !visited[pos - 1]) {
				queue.add(new int[] {pos - 1, cnt + 1});
				visited[pos - 1] = true;
			}
			
			if (0 <= 2 * pos && 2 * pos < 100_001 && !visited[2 * pos]) {
				queue.add(new int[] {2 * pos, cnt + 1});
				visited[2 * pos] = true;
			}
		}
		
		return -1;
	}
}
