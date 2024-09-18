import java.io.*;
import java.util.*;

public class Main {
    static int N, M, R;
    static int[] itemCount;
    static int[] distance;
    static ArrayList<Edge>[] edges;
    static final int INF = 10000000;

    public static class Edge implements Comparable<Edge>{
        public int to;
        public int cost;

        public Edge(int to, int cost){
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void Dijkstra(int start){
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            int from = edge.to;
            int cost = edge.cost;

            for(Edge edge2 : edges[from]){
                if(cost + edge2.cost < distance[edge2.to]){
                    distance[edge2.to] = cost + edge2.cost;
                    pq.add(new Edge(edge2.to, distance[edge2.to]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        distance = new int[N + 1];
        itemCount = new int[N + 1];
        edges = new ArrayList[N + 1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            itemCount[i] = Integer.parseInt(st.nextToken());
            edges[i] = new ArrayList<>();
        }

        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(b, cost));
            edges[b].add(new Edge(a, cost));
        }

        int max = 0;
        for(int i = 1; i <= N; i++){
            Dijkstra(i);
            int temp = 0;
            for(int j = 1; j <= N; j++){
                if(distance[j] <= M){
                    temp += itemCount[j];
                }
            }
            max = Math.max(max, temp);
        }

        System.out.print(max);
    }
}