import java.io.*;
import java.util.*;

public class Main {
    static List<Node>[] graph;
    static boolean[] visited;
    static int result;
    static int farNode;

    static class Node {
        public int n;
        public int dist;

        Node(int n, int dist){
            this.n = n;
            this.dist = dist;
        }
    }

    static void dfs(int n, int dist){
        visited[n] = true;

        if (dist > result) {
            result = dist;
            farNode = n;
        }

        for(Node node : graph[n]){
            if (!visited[node.n]) {
                dfs(node.n, dist + node.dist);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String line;
        graph = new ArrayList[10001];
        visited = new boolean[10001];

        for (int i = 0; i < 10001; i++) {
            graph[i] = new ArrayList<>();
        }

        while ((line = br.readLine()) != null && !line.isEmpty()) {
            st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, dist));
            graph[b].add(new Node(a, dist));
        }

        result = 0;
        dfs(1, 0);

        Arrays.fill(visited, false);
        dfs(farNode, 0);

        System.out.print(result);
    }
}
