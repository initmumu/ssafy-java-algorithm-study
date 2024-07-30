import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ18258 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Deque<Integer> queue = new ArrayDeque<>();
		StringBuilder sb = new StringBuilder();
		
		while (N-- > 0) {
			String[] arr = br.readLine().split(" ");
			int b = 0;
			switch (arr[0]) {
				case "push":
					b = Integer.parseInt(arr[1]);
					queue.offerLast(b);
					break;
				case "pop":
					if (!queue.isEmpty()) {
						sb.append(queue.removeFirst()).append("\n");
					} else {
						sb.append(-1).append("\n");
					}
					break;
				case "size":
					sb.append(queue.size()).append("\n");
					break;
				case "empty":
					if (!queue.isEmpty()) {
						sb.append(0).append("\n");
					} else {
						sb.append(1).append("\n");
					}
					break;
				case "front":
					if (!queue.isEmpty()) {
						sb.append(queue.getFirst()).append("\n");
					} else {
						sb.append(-1).append("\n");
					}
					break;
				case "back":
					if (!queue.isEmpty()) {
						sb.append(queue.getLast()).append("\n");
					} else {
						sb.append(-1).append("\n");
					}
					break;
			}
			
		}
		
		System.out.println(sb);

	}

}
