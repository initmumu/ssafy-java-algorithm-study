import java.io.*;
import java.util.*;

public class BOJ1325 {

    static int N, M;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph[B].add(A);
        }

        int maxCount = 0;
        int[] hackCount = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            hackCount[i] = BFS(i);
            maxCount = Math.max(maxCount, hackCount[i]);
        }

        StringBuilder result = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (hackCount[i] == maxCount) {
                result.append(i).append(" ");
            }
        }

        System.out.println(result);
    }

    static int BFS(int start) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.add(start);
        visited[start] = true;

        int count = 0;
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                    count++;
                }
            }
        }

        return count;
    }
}
