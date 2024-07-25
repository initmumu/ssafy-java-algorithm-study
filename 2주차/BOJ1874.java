import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1874 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		Stack<Integer> stack = new Stack<>();
		
		int count = 1;
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			int current = Integer.parseInt(br.readLine());
			
			if (stack.isEmpty()) {
				stack.add(count++);
				sb.append("+\n");
			}
			
			if (stack.peek() != current) {
				if (count <= current) {
					while (count <= current) {
						stack.add(count++);
						sb.append("+\n");
					}
				} else {
					if (stack.pop() == current) sb.append("-\n");
					else {
						System.out.println("NO");
						return;
					}
				}
			}
			
			if (stack.peek() == current) {
				stack.pop();
				sb.append("-\n");
			}
		}
		
		System.out.println(sb.toString());
	}
}
