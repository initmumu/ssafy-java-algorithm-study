import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_10773 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Stack<Integer> stack = new Stack<>();
		
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			int money = Integer.parseInt(br.readLine());
			if (money == 0) stack.pop();
			else stack.push(money);
		}
		
		int sum = 0;
		for (Integer money : stack) {
			sum += money;
		}
		
		System.out.println(sum);
	}
}
