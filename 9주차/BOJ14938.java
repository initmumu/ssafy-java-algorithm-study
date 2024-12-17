import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ14938 {

    public static class Node implements Comparable<Node> {

        int to, weight;

        public Node(int to, int weight) {
            super();
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }

    public static int N, M, R;
    public static int itemCost[];
    public static ArrayList<ArrayList<Node>> graph;
    public static final int INF = Integer.MAX_VALUE;

    public static int dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int to = node.to;
            int weight = node.weight;
			if (weight > dist[to]) {
				continue;
			}
            for (Node next : graph.get(to)) {
                int nextTo = next.to;
                int nextWeight = next.weight;
                int newDist = dist[to] + nextWeight;
                if (newDist < dist[nextTo] && newDist <= M) {
                    dist[nextTo] = newDist;
                    pq.offer(new Node(nextTo, newDist));
                }
            }
        }
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] != INF) {
                sum += itemCost[i];
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        itemCost = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            itemCost[i] = Integer.parseInt(st.nextToken());
        }
        graph = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Node>());
        }
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, weight));
            graph.get(b).add(new Node(a, weight));
        }
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(dijkstra(i), result);
        }
        System.out.println(result);
    }

}
