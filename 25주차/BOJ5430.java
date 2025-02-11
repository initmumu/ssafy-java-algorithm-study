import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            String commands = br.readLine(); // 수행할 명령어
            int n = Integer.parseInt(br.readLine()); // 배열 크기

            // 입력된 배열을 파싱
            Deque<Integer> deque = new ArrayDeque<>();
            String input = br.readLine();
            if (n > 0) {
                String[] elements = input.substring(1, input.length() - 1).split(",");
                for (String elem : elements) {
                    deque.add(Integer.parseInt(elem));
                }
            }

            // R 연산을 뒤집지 않고 boolean 변수로 관리
            boolean reversed = false;
            boolean error = false;

            for (char cmd : commands.toCharArray()) {
                if (cmd == 'R') {
                    reversed = !reversed;
                } else { // 'D'
                    if (deque.isEmpty()) {
                        sb.append("error\n");
                        error = true;
                        break;
                    }
                    if (reversed) {
                        deque.pollLast();
                    } else {
                        deque.pollFirst();
                    }
                }
            }

            if (!error) {
                sb.append("[");
                while (!deque.isEmpty()) {
                    sb.append(reversed ? deque.pollLast() : deque.pollFirst());
                    if (!deque.isEmpty()) sb.append(",");
                }
                sb.append("]\n");
            }
        }

        System.out.print(sb.toString());
    }
}
