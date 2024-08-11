import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15652 {
	
	public static int N, M;
	public static boolean [] visited;
	public static StringBuilder sb = new StringBuilder();
	public static int [] arr;
	
	public static void dfs(int m, int s) {
		if (m == M) {
			for (int index: arr) {
				sb.append(index + " ");
			}
			sb.append("\n");
			return;
		}
		for (int i = s; i <= N; i++) {
			arr[m] = i;
			dfs(m+1, i);
		}
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		visited = new boolean[N+1];
		arr = new int[M];
		dfs(0, 1);
		System.out.println(sb);
	}

}
