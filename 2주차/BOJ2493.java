import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ2493 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Stack<int[]> stack = new Stack<>();
		for (int i = 1; i <= N; i++) {
			int n = Integer.parseInt(st.nextToken());
			
			while (!stack.isEmpty()) {
				if (stack.peek()[1] >= n) {
					sb.append(stack.peek()[0]).append(" ");
					break;
				}
				stack.pop();
			}
			
			if (stack.isEmpty()) {
				sb.append("0").append(" ");
			}
			
			stack.add(new int[] {i, n});
		}
		System.out.println(sb);
		
	}

}
