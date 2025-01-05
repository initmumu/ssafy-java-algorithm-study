import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer>[] tree;
    static boolean[] visited;
    static int[] parents;

    public static void dfs(int parent){
        visited[parent] = true;
        for(int child : tree[parent]){
            if(visited[child]) continue;
            parents[child] = parent + 1;
            dfs(child);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        parents = new int[N];
        visited = new boolean[N];

        tree = new ArrayList[N];

        for(int i = 0; i < N; i++){
            tree[i] = new ArrayList<>();
        }

        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;

            tree[a].add(b);
            tree[b].add(a);
        }

        dfs(0);

        for(int i = 1; i < N; i++){
            System.out.println(parents[i]);
        }
    }
}
