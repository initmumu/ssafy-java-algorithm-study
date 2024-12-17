import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16202_Kruskal {

    public static int N, M, K;
    public static int[][] graph;
    public static StringBuilder sb = new StringBuilder();

    public static int find(int a, int[] parent) {
        if (parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a], parent);
    }

    public static boolean union(int a, int b, int[] parent) {
        int rootA = find(a, parent);
        int rootB = find(b, parent);

        if (rootA == rootB) {
            return false;
        }

        if (rootA < rootB) {
            parent[rootB] = rootA;
        } else {
            parent[rootA] = rootB;
        }

        return true;
    }

    public static boolean kruskal(int index) {
        int[] parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int count = 0;
        int cost = 0;
        for (int i = index; i < M; i++) {
            if (union(graph[i][0], graph[i][1], parent)) {
                count++;
                cost += graph[i][2];
                if (count == N - 1) {
                    break;
                }
            }
        }

        if (count == N - 1) {
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

        graph = new int[M][3];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[i][0] = a;
            graph[i][1] = b;
            graph[i][2] = i + 1;
        }

        for (int i = 0; i < K; i++) {
            if (kruskal(i)) {
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
