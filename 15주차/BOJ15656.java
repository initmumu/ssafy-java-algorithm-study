import java.io.*;
import java.util.*;

public class BOJ15656 {
	
	static int N, M;
	static int[] arr, seq;
	static StringBuilder result = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		arr = new int[N];
		seq = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		
		DFS(0);
		System.out.println(result);
	}
	
	static void DFS(int depth) {
		if (depth == M) {
			for (int i = 0; i < M; i++) {
				result.append(seq[i]).append(" ");
			}
			result.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			seq[depth] = arr[i];
			DFS(depth + 1);
		}
	}
}
