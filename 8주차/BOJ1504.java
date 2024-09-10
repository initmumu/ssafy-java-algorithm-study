import java.io.*;
import java.util.*;

public class Main {
    public static class Node implements Comparable<Node> {
        int to;
        int cost;

        Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static int[] Dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] distance = new int[N + 1];
        Arrays.fill(distance, INF);
        distance[start] = 0;
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node curNode = pq.poll();
            int curTo = curNode.to;
            int curCost = curNode.cost;

            if(curCost > distance[curTo]) continue;

            for(Node neighbor : graph.get(curTo)) {
                int temp = distance[curTo] + neighbor.cost;
                if(temp < distance[neighbor.to]) {
                    distance[neighbor.to] = temp;
                    pq.add(new Node(neighbor.to, temp));
                }
            }
        }
        return distance;
    }

    static List<List<Node>> graph;
    static int N;
    static final int INF = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, cost));
            graph.get(b).add(new Node(a, cost));
        }

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[] startDij = Dijkstra(1);
        int[] endDij = Dijkstra(N);
        int[] ADij = Dijkstra(A);

        int result = Math.min(startDij[A] + ADij[B] + endDij[B], startDij[B] + ADij[B] + endDij[A]);

        System.out.print(result >= INF ? -1 : result);
    }
}
