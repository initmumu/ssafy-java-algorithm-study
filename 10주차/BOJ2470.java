import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ2470 {

	public static int N;
	public static int graph[];
	public static int comb[];
	public static int min_value = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int [N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			graph[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(graph);
		
		comb = new int[2];
		
		for (int i = 0; i < N-1; i++) {
			int target = -graph[i];
			int index = binarySearch(i + 1, target);
			int sum = graph[i] + graph[index];
			if (Math.abs(sum) < min_value) {
				min_value = Math.abs(sum);
				comb[0] = graph[i];
				comb[1] = graph[index];
			}
		}
		
		Arrays.sort(comb);
		
		System.out.println(comb[0] + " " + comb[1]);
		
	}
	
	public static int binarySearch(int start, int target) {
		int low = start;
		int high = N-1;
		
		while (low < high) {
			int mid = (low + high) / 2;
			if (graph[mid] < target) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		
		if (low == start) return low;
		
		if (Math.abs(graph[low] - target) < Math.abs(graph[low - 1] - target)) {
			return low;
		} else {
			return low - 1;
		}
	}

}
