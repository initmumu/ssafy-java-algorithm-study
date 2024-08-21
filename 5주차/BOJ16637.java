import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ16637 {
	
	static int N;
	static int[] nums;
	static char[] op;
	static int max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String expression = br.readLine();
		
		nums = new int[N];
        op = new char[N / 2];
		
		for (int i = 0; i < N; i++) {
			if (i % 2 == 0)
				nums[i/2] = expression.charAt(i) - '0';
			else
				op[i/2] = expression.charAt(i);
		}
		
		DFS(0, nums[0]);
		System.out.println(max);
	}
	
	static void DFS(int depth, int current) {
		if (depth >= op.length) {
			max = Math.max(max, current);
			return;
		}
		
		// 1. 현재 연산자 주위에 괄호를 추가 하지 않음
		DFS(depth + 1, calculate(current, op[depth], nums[depth + 1]));
		// 2. 현재 연산자 주위에 괄호를 추가
		if (depth + 1 < op.length) {
			int next = calculate(nums[depth + 1], op[depth + 1], nums[depth + 2]);
			DFS(depth + 2, calculate(current, op[depth], next));
		}
		
	}
	
	private static int calculate(int a, char op, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        }
        return 0;
    }

}
