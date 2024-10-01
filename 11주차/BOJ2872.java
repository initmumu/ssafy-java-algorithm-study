import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2872 {
	
	public static int N;
	public static Integer graph [];

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new Integer [N];
		for (int i = 0; i < N; i++) {
			graph[N-1-i] = Integer.parseInt(br.readLine());
		}
		
		int target = N;
		int result = 0;
		
		for (int i = 0; i < N; i++) {
			if (graph[i] == target) {
				target--;
			} else {
				result++;
			}
		}
		
		System.out.println(result);
		
	}

}
