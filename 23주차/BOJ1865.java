import java.io.*;
import java.util.*;

public class Main {
    static int T;
    static int[] distance;
    static ArrayList<Node> edges;
    static int N, M, W;

    public static class Node{
        public int start;
        public int end;
        public int distance;

        public Node(int start, int end, int distance){
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    public static boolean bellman_ford(){
        Arrays.fill(distance, 200000);
        distance[0] = 0;

        for(int i = 0; i < N; i++){
            for (Node edge : edges) {
                int s = edge.start;
                int e = edge.end;
                int d = edge.distance;

                if (distance[s] != Integer.MAX_VALUE && distance[e] > distance[s] + d) {
                    distance[e] = distance[s] + d;
                    if (i == N - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            distance = new int[N];
            edges = new ArrayList<>();

            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()) - 1;
                int E = Integer.parseInt(st.nextToken()) - 1;
                int T = Integer.parseInt(st.nextToken());

                edges.add(new Node(S, E, T));
                edges.add(new Node(E, S, T));
            }

            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken()) - 1;
                int E = Integer.parseInt(st.nextToken()) - 1;
                int T = Integer.parseInt(st.nextToken());

                edges.add(new Node(S, E, -T));
            }

            if(bellman_ford()){
                sb.append("YES").append('\n');
            } else {
                sb.append("NO").append('\n');
            }
        }

        System.out.print(sb);
    }
}
