import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ17298 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();
		for (int i = N-1; i >= 0; i--) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i<N; i++) {
			int n = arr[i];
			StringBuilder sb2 = new StringBuilder();
			
			while(!stack.isEmpty()) {
				if (stack.peek() > n) {
					sb2.append(stack.peek()).reverse();
					sb.append(" ").append(sb2);
					break;
				}
				stack.pop();
			}
			
			if (stack.isEmpty()) {
				sb.append(" ").append("1-");
			}
			
			stack.add(n);
		}
		
		System.out.println(sb.reverse());

	}

}
