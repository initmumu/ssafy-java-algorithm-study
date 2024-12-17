import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ1504 {

    public static class Node implements Comparable<Node> {

        int from, weight;

        public Node(int from, int weight) {
            this.from = from;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }


    public static int N, E;
    public static ArrayList<ArrayList<Node>> graph;
    public static final int INF = Integer.MAX_VALUE;

    public static int dijkstra(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int from = node.from;
            int weight = node.weight;
            if (from == end) {
                return dist[end];
            }
			if (weight > dist[from]) {
				continue;
			}

            for (Node next : graph.get(from)) {
                int nextTo = next.from;
                int nextWeight = next.weight;
                int newDist = dist[from] + nextWeight;

                if (newDist < dist[nextTo]) {
                    dist[nextTo] = newDist;
                    pq.add(new Node(nextTo, newDist));
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, weight));
            graph.get(b).add(new Node(a, weight));
        }
        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int q = dijkstra(1, a);
        int w = dijkstra(a, b);
        int e = dijkstra(b, N);
        int first = 0;
        if (q == -1 || w == -1 || e == -1) {
            first = -1;
        } else {
            first = q + w + e;
        }

        int r = dijkstra(1, b);
        int t = dijkstra(b, a);
        int y = dijkstra(a, N);
        int second = 0;
        if (r == -1 || t == -1 || y == -1) {
            second = -1;
        } else {
            second = r + t + y;
        }

        int result = 0;
        if (first == -1 || second == -1) {
            result = -1;
        } else {
            result = Math.min(first, second);
        }

        System.out.println(result);
    }

}
