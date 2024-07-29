import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;

public class BOJ17298 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		int[] A = Arrays.stream(br.readLine()
				.split(" "))
				.mapToInt(Integer::parseInt)
				.toArray();

		Stack<Integer> stack = new Stack<>();
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && A[stack.peek()] < A[i]) {
				A[stack.pop()] = A[i];
			}
			stack.push(i);
		}

		while (!stack.isEmpty())
			A[stack.pop()] = -1;

		for (Integer num : A) {
			sb.append(num + " ");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
