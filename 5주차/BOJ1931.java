import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1931 {
	
	public static int N;
	public static int [][] graph;
	

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		graph = new int [N][2];
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			graph[i][0] = start;
			graph[i][1] = end;
		}
		
		Arrays.sort(graph, (o1, o2) -> {
			if (o1[1] == o2[1]) return o1[0] - o2[0];
			return o1[1] - o2[1];
		});
		ArrayList<int[]> result = new ArrayList<>();
		result.add(new int[] {graph[0][0], graph[0][1]});
		for (int i = 1; i < N; i++) {
			if (result.get(result.size()-1)[1] <= graph[i][0]) {
				result.add(new int[] {graph[i][0], graph[i][1]});
			}
		}
		System.out.println(result.size());

	}

}
