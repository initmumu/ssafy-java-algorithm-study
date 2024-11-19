import java.io.*;
import java.util.*;

public class BOJ1966 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Queue<int[]> queue = new LinkedList<>();
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                queue.add(new int[] { Integer.parseInt(st.nextToken()), i });
            }

            int order = 0;
            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                boolean printTurn = true;

                for (int[] doc : queue) {
                    if (doc[0] > cur[0]) {
                        printTurn = false;
                        break;
                    }
                }

                if (printTurn) {
                    order++;
                    if (cur[1] == M) {
                        System.out.println(order);
                        break;
                    }
                }
                else {
                    queue.add(cur);
                }
            }
        }
    }
}
