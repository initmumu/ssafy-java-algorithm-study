import java.io.*;
import java.util.*;
public class Main {
	static int N, M;
	static int[] nums;
	static int[] temps;
	static StringBuilder sb;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sb = new StringBuilder();
		nums = new int[N];
		temps = new int[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(nums);
		dfs(0, 0);
		System.out.print(sb);
	}
	
	public static void dfs(int depth, int n) {
		if(depth == M) {
			for(int a : temps) {
				sb.append(a).append(' ');
			}
			sb.append('\n');
			return;
		}
		
		int prev = -1;
		for(int i = 0; i < N; i++) {
			if(prev == nums[i]) continue;
			temps[depth] = nums[i];
			dfs(depth + 1, i);
			prev = nums[i];
		}
	}

}
