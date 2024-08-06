import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1260 {
    public static int[][] graph;
    public static boolean[] check;
    public static Queue<Integer> queue = new LinkedList<>();
    public static StringBuilder dfs = new StringBuilder();
    public static StringBuilder bfs = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());

        graph = new int[N+1][N+1];
        check = new boolean[N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a][b] = graph[b][a] = 1;
        }
        Arrays.fill(check, false);
        DFS(V);
        System.out.println(dfs.toString());

        Arrays.fill(check, false);
        BFS(V);
        System.out.println(bfs.toString());
    }

    public static void DFS(int V) {
        dfs.append(V).append(" ");
        check[V] = true;

        for (int v = 0; v < graph[V].length; v++) {
            if (graph[V][v] == 1 && !check[v]) DFS(v);
        }
    }

    public static void BFS(int V) {
        queue.add(V);
        check[V] = true;

        while (!queue.isEmpty()) {
            V = queue.poll();
            bfs.append(V).append(" ");

            for (int v = 1; v <= graph.length-1; v++) {
                if (graph[V][v] == 1 && !check[v]) {
                    queue.add(v);
                    check[v] = true;
                }
            }
        }
    }
}
