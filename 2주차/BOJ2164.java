import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ2164 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Deque<Integer> deque = new ArrayDeque<Integer>();
		
		for (int i = 1; i <= N; i++) {
			deque.addFirst(i);
		}
		
		while(deque.size() > 1) {
			deque.pollLast();
			deque.addFirst(deque.pollLast());
		}
		
		System.out.println(deque.pop());

	}

}
