import java.io.*;
import java.util.*;

public class BOJ11779 {

    static class Node implements Comparable<Node> {
        int v, cost;

        Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, M;
    static List<Node>[] graph;

    static int[] distance;
    static int[] previous;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new ArrayList[N + 1];
        distance = new int[N + 1];
        previous = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(distance, Integer.MAX_VALUE);

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u].add(new Node(v, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start);

        System.out.println(distance[end]);

        List<Integer> path = new ArrayList<>();

        while (end != 0) {
            path.add(end);
            end = previous[end];
        }

        Collections.reverse(path);

        System.out.println(path.size());

        for (int city: path) {
            System.out.print(city + " ");
        }
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            int cur = pq.poll().v;

            if (visited[cur]) {
                continue;
            }
            visited[cur] = true;

            for (Node next : graph[cur]) {
                if (distance[next.v] > distance[cur] + next.cost) {
                    distance[next.v] = distance[cur] + next.cost;
                    previous[next.v] = cur;
                    pq.add(new Node(next.v, distance[next.v]));
                }
            }
        }
    }
}
