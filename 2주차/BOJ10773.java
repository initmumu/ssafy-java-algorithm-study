import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ10773 {

	public static void main(String[] args) throws IOException {
		Stack<Integer> stack = new Stack<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		int sum = 0;
		for (int i = 0; i < K; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) {
				sum -= stack.pop();
				continue;
			}
			stack.add(n);
			sum += n;
		}
		System.out.println(sum);
	}

}
