import java.util.*;
import java.io.*;

public class BOJ21278 {

    public static class Node implements Comparable<Node> {
        int index;
        int dist;

        public Node(int index, int dist) {
            this.index = index;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }

    public static int N, M;
    public static List<List<Integer>> graph;
    public static int[][] allDist;
    public static int[] selected;
    public static final int INF = Integer.MAX_VALUE;
    public static int building1, building2;
    public static int minDist = INF;

    public static void totalDist(int a, int b) {
        int totalDist = 0;
        for (int i = 1; i <= N; i++) {
            totalDist += 2 * Math.min(allDist[i][a], allDist[i][b]);
        }

        if (totalDist < minDist) {
            minDist = totalDist;
            building1 = a;
            building2 = b;
        } else if (totalDist == minDist) {
            if (a < building1 || (a == building1 && b < building2)) {
                building1 = a;
                building2 = b;
            }
        }
    }

    public static void dfs(int start, int depth) {
        if (depth == 2) {
            totalDist(selected[0], selected[1]);
            return;
        }

        for (int i = start; i <= N; i++) {
            selected[depth] = i;
            dfs(i + 1, depth + 1);
        }
    }

    public static void dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int currentIndex = node.index;
            int currentDist = node.dist;

            if (currentDist > dist[currentIndex]) continue;

            for (int neighbor : graph.get(currentIndex)) {
                int newDistance = currentDist + 1;

                if (newDistance < dist[neighbor]) {
                    dist[neighbor] = newDistance;
                    pq.offer(new Node(neighbor, newDistance));
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            allDist[start][i] = dist[i];
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        allDist = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            dijkstra(i);
        }

        selected = new int[2];
        dfs(1, 0);

        System.out.println(building1 + " " + building2 + " " + minDist);
    }
}
