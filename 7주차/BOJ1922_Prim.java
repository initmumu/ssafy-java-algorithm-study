import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class BOJ1922_Prim {

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

    public static int N, M;
    public static ArrayList<ArrayList<Node>> graph;
    public static PriorityQueue<Node> queue;
    public static boolean visited[];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList<ArrayList<Node>>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Node>());
        }
        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, weight));
            graph.get(b).add(new Node(a, weight));
        }
        queue = new PriorityQueue<Node>();
        visited = new boolean[N + 1];

        queue.offer(new Node(1, 0));

        int cost = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int to = node.to;
            int weight = node.weight;

			if (visited[to]) {
				continue;
			}
            visited[to] = true;
            cost += weight;

            for (Node next : graph.get(to)) {
				if (visited[next.to]) {
					continue;
				}
                queue.offer(next);
            }
        }

        System.out.println(cost);

    }

}
