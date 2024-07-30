import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class BOJ2164 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		Deque<Integer> deque = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			deque.add(i);
		}
		for (int i = 0; i < N - 1; i++) {
			deque.removeFirst();
			deque.add(deque.pollFirst());
		}
		System.out.println(deque.peek());
	}

}
