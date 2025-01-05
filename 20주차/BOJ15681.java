import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static List<Integer>[] tree;
    static boolean[] visited;
    static int[] counts;

    public static int dfs(int cur){
        visited[cur] = true;
        counts[cur] = 1;

        for(int n : tree[cur]){
            if(visited[n]) continue;

            counts[cur] += dfs(n);
        }

        return counts[cur];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        tree = new ArrayList[N + 1];
        visited = new boolean[N + 1];
        counts = new int[N + 1];

        for(int i = 1; i <= N; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i =0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(R);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < Q; i++){
            int q = Integer.parseInt(br.readLine());
            sb.append(counts[q]).append('\n');
        }

        System.out.print(sb);

    }
}
