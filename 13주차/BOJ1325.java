import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1325 {

	public static int N, M;
	public static List<List<Integer>> graph;
	public static int [] lenArr;
	public static int max_value = 0;
    public static boolean [] visited;
	
	public static void bfs(int start) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;
		while (!queue.isEmpty()) {
			int poll = queue.poll();
			int size = graph.get(poll).size();
			for (int i = 0; i < size; i++) {
				int next = graph.get(poll).get(i);
                if (visited[next]) continue;
                visited[next] = true;
				lenArr[next]++;
				queue.add(next);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		
		for (int i = 0; i <= N; i++) {
			graph.add(new ArrayList<>());
		}
		
		lenArr = new int[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph.get(a).add(b);
		}
		
		for (int i = 1; i <= N; i++) {
            visited = new boolean [N+1];
			bfs(i);
		}
        for (int i = 1; i <= N; i++) {
            if (lenArr[i] > max_value) max_value = lenArr[i];
        }
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i <= N; i++) {
			if (max_value == lenArr[i]) sb.append(i + " ");
		}
		System.out.println(sb);
	}

}
