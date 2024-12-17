import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ16202_Prim {

    public static class Node implements Comparable<Node> {

        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static int N, M, K;
    public static ArrayList<ArrayList<Node>> graph;
    public static StringBuilder sb = new StringBuilder();

    public static boolean prim(int startEdgeIndex) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        queue.offer(new Node(1, 0));

        int cost = 0;
        int count = 0;

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int to = node.to;
            int weight = node.weight;

            if (visited[to]) {
                continue;
            }
            visited[to] = true;
            cost += weight;
            count++;

            for (Node next : graph.get(to)) {
                if (!visited[next.to] && next.weight >= startEdgeIndex) {
                    queue.offer(next);
                }
            }
        }

        if (count == N) {
            sb.append(cost + " ");
            return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, i + 1));
            graph.get(b).add(new Node(a, i + 1));
        }

        for (int i = 0; i < K; i++) {
            if (prim(i + 1)) {
                continue;
            } else {
                for (int j = i; j < K; j++) {
                    sb.append("0 ");
                }
                break;
            }
        }

        System.out.println(sb);
    }
}
