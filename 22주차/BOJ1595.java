import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ1595 {

    static List<List<Node>> graph = new ArrayList<>();
    static boolean[] visited;
    static int maxResult;
    static int maxNode;

    static class Node {

        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 10001; i++) {
            graph.add(new ArrayList<>());
        }

        StringTokenizer st;
        String s;
        while ((s = br.readLine()) != null && !s.isEmpty()) {
            st = new StringTokenizer(s);

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, w));
            graph.get(b).add(new Node(a, w));
        }
        maxResult = 0;
        maxNode = 0;
        visited = new boolean[graph.size()];
        dfs(1, 0);

        maxResult = 0;
        visited = new boolean[graph.size()];
        dfs(maxNode, 0);

        System.out.println(maxResult);
    }

    static void dfs(int node, int dist) {
        visited[node] = true;

        if (dist > maxResult) {
            maxResult = dist;
            maxNode = node;
        }

        for (Node node2 : graph.get(node)) {
            if (!visited[node2.to]) {
                dfs(node2.to, dist + node2.weight);
            }
        }
    }
}
