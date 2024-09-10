import java.io.*;
import java.util.*;

public class Main {
    public static class Edge implements Comparable<Edge> {
        public int from;
        public int to;
        public int cost;

        public Edge(int from, int to, int cost){
            this.from = from;
            this.to = to;
            this.cost = cost;
        }


        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int N, M;
    static int[] parents;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> edges = new PriorityQueue<>();

        parents = new int[N + 1];
        for(int n = 1; n <= N; n++){
            parents[n] = n;
        }

        for(int m = 0; m < M; m++){
            st = new StringTokenizer(br.readLine());
            edges.add(new Edge(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        int max = 0;
        int result = 0;
        while(!edges.isEmpty()){
            Edge edge = edges.poll();
            int from = edge.from;
            int to = edge.to;
            int cost = edge.cost;

            if(getParent(from) == getParent(to)) continue;
            setParent(from, to);
            result += cost;
            max = Math.max(max, cost);
        }

        System.out.print(result - max);
    }

    public static int getParent(int a){
        if(parents[a] == a) return a;
        else return parents[a] = getParent(parents[a]);
    }

    public static void setParent(int a, int b){
        int ap = getParent(a);
        int bp = getParent(b);
        if(ap != bp) parents[ap] = bp;
    }
}
