import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        long value;
        int count;

        Node(long value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        System.out.println(bfs(A, B));
    }

    static int bfs(long A, long B) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(A, 1)); // 시작 값과 연산 횟수(1부터 시작)

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            if (current.value == B) {
                return current.count;
            }

            long next1 = current.value * 2;
            long next2 = current.value * 10 + 1;

            if (next1 <= B) {
                queue.add(new Node(next1, current.count + 1));
            }
            if (next2 <= B) {
                queue.add(new Node(next2, current.count + 1));
            }
        }

        return -1;
    }
}
