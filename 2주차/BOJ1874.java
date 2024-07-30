import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1874 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine());
		Stack<Integer> stack = new Stack<Integer>();
		
		int start = 0;
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (start < n) {
				for (int j = start + 1; j <= n; j++) {
					stack.add(j);
					sb.append("+").append("\n");
				}
				start = n;
			} 
			if (stack.peek() == n) {
				stack.pop();
				sb.append("-").append("\n");
			}
		}
		
		if (stack.size() > 0) {
			System.out.println("NO");
		} else {
			System.out.println(sb);
		}

	}

}
