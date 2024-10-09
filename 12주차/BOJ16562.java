import java.io.*;
import java.util.*;

public class BOJ16562 {

    static int[] A;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        A = new int[N + 1];
        parent = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            parent[i] = i;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            union(v, w);
        }

        // 각 그룹의 최소 친구 비용
        int[] minCost = new int[N + 1];
        Arrays.fill(minCost, Integer.MAX_VALUE);

        for (int i = 1; i <= N; i++) {
            int root = find(i);
            minCost[root] = Math.min(minCost[root], A[i]);
        }

        int totalCost = 0;
        for (int i = 1; i <= N; i++) {
            if (minCost[i] != Integer.MAX_VALUE) {
                totalCost += minCost[i];
            }
        }

        if (totalCost <= k) {
            System.out.println(totalCost);
        }
        else {
            System.out.println("Oh no");
        }
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }
}
