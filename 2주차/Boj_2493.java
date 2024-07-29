import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Boj_2493 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		Deque<Integer> queue = new ArrayDeque<Integer>();
		while (N-- > 0) {
			int n = Integer.parseInt(st.nextToken());
			queue.push(n);
//			sb.append(n).append(" ");
		}
		System.out.println(queue);
		while(!queue.isEmpty()) {
		}

	}

}
