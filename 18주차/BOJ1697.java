import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1697 {

	public static int N, K;
	public static int[] visited = new int[100001];

	public static void bfs() {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(N);
		visited[N] = 1;

		while (!queue.isEmpty()) {
			int n = queue.poll();
			if (n == K) {
				System.out.println(visited[n] - 1);
				break;
			}
			int [] nextArr = {n-1, n+1, n*2};
			for (int next: nextArr) {
				if (next < 0 || next > 100000 || visited[next] != 0) continue;
				visited[next] = visited[n] + 1;
				queue.add(next);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		bfs();
	}
}
