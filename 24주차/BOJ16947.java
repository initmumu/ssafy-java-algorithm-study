import java.io.*;
import java.util.*;

public class BOJ16947 {
    static int N;
    static List<Integer>[] graph;
    static boolean[] visited, cycle;
    static int[] distance;
    
    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        N = in.readInt();
        
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int a = in.readInt();
            int b = in.readInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        visited = new boolean[N + 1];
        cycle = new boolean[N + 1];

        findCycle(1, -1);

        distance = new int[N + 1];
        Arrays.fill(distance, -1);

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(distance[i]).append(" ");
        }
        System.out.println(sb);
    }

    static boolean findCycle(int node, int parent) {
        visited[node] = true;

        for (int next : graph[node]) {
            if (!visited[next]) {
                if (findCycle(next, node)) {
                    if (!cycle[node]) cycle[node] = true;
                    return true;
                }
            } else if (next != parent) {
                cycle[next] = true;
                cycle[node] = true;
                return true;
            }
        }
        return false;
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            if (cycle[i]) {
                queue.add(i);
                distance[i] = 0;
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (distance[next] == -1) {
                    distance[next] = distance[cur] + 1;
                    queue.add(next);
                }
            }
        }
    }

    static class InputReader {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int cur, size;

        public InputReader(InputStream in) {
            this.in = in;
        }

        private int next() throws IOException {
            if (cur >= size) {
                cur = 0;
                size = in.read(buffer);
                if (size == -1) return -1;
            }
            return buffer[cur++];
        }

        public int readInt() throws IOException {
            int num = 0, c;
            boolean neg = false;

            while ((c = next()) <= ' ') ;
            if (c == '-') {
                neg = true;
                c = next();
            }
            do {
                num = num * 10 + (c - '0');
            } while ((c = next()) >= '0' && c <= '9');

            return neg ? -num : num;
        }
    }
}