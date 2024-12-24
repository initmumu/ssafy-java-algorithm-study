import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ12851 {

    static int N, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        N = Integer.parseInt(input[0]);
        K = Integer.parseInt(input[1]);

        if (N == K) {
            System.out.println("0\n1");
        }
        else {
            BFS();
        }
    }

    static void BFS() {
        Queue<Integer> queue = new LinkedList<>();
        int[] visited = new int[100001];
        visited[N] = 1;
        queue.add(N);

        int time = 0;
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int cur = queue.poll();

                if (cur == K) {
                    count++;
                }

                for (int next : new int[]{cur - 1, cur + 1, cur * 2}) {
                    if (next < 0 || next > 100000) {
                        continue;
                    }

                    if (visited[next] == 0 || visited[next] == visited[cur] + 1) {
                        visited[next] = visited[cur] + 1;
                        queue.add(next);
                    }
                }
            }

            if (count > 0) {
                System.out.println(time);
                System.out.println(count);
                return;
            }

            time++;
        }
    }
}
