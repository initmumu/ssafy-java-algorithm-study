import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class BOJ5430 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            String P = br.readLine();
            int N = Integer.parseInt(br.readLine());
            String input = br.readLine();

            Deque<Integer> deque = new ArrayDeque<>();

            if (N != 0) {
                String[] nums = input.substring(1, input.length() - 1).split(",");
                for (String num : nums) {
                    deque.add(Integer.parseInt(num));
                }
            }

            boolean R = false;
            boolean isError = false;

            for (char command : P.toCharArray()) {
                // R (뒤집기)
                if (command == 'R') {
                    R = !R;
                }
                // D (버리기)
                else if (command == 'D') {
                    if (deque.isEmpty()) {
                        sb.append("error\n");
                        isError = true;
                        break;
                    }
                    if (R) {
                        deque.removeLast();
                    }
                    else {
                        deque.removeFirst();
                    }
                }
            }

            if (isError) {
                continue;
            }

            sb.append("[");
            while (!deque.isEmpty()) {
                sb.append(R ? deque.removeLast() : deque.removeFirst());
                if (!deque.isEmpty()) {
                    sb.append(",");
                }
            }
            sb.append("]\n");
        }

        System.out.println(sb);
    }
}
