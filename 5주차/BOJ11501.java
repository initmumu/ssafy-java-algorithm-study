import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11501 {
	
	public static int N;
	public static int [] graph;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			graph = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = N-1; i >= 0; i--) {
				graph[i] = Integer.parseInt(st.nextToken());
			}
			
			long result = 0;
			long max_num = graph[0];
			for (int i = 1; i < N; i++) {
				if (graph[i] >= max_num) {
					max_num = graph[i];
				} else {
					result += max_num - graph[i];
				}
			}
			System.out.println(result);
		}

	}

}
