import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Queue;
import java.util.ArrayDeque;

public class BOJ1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            Queue<int[]> queue = new ArrayDeque<>();
            for (int i = 0; i < n; i++) {
                int p = Integer.parseInt(st.nextToken());
                queue.add(new int[]{i, p});
            }

            int result = 0;

            while (!queue.isEmpty()) {
                int[] poll = queue.poll();
                boolean flag = false;

                for (int[] num : queue) {
                    if (num[1] > poll[1]) {
                        flag = true;
                        break;
                    }
                }

                if (flag) {
                    queue.add(poll);
                } else {
                    result++;
                    if (poll[0] == m) {
                        sb.append(result).append("\n");
                        break;
                    }
                }
            }
        }

        System.out.print(sb);
    }
}
