import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1967 {

    static class Node {

        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static List<Node>[] tree;
    static boolean[] visited;
    static int maxResult;
    static int maxNode = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        tree = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            tree[a].add(new Node(b, w));
            tree[b].add(new Node(a, w));
        }

        maxResult = 0;
        visited = new boolean[n + 1];
        bfs(1);

        visited = new boolean[n + 1];
        maxResult = 0;
        bfs(maxNode);

        System.out.println(maxResult);
    }

    static void bfs(int start) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(start, 0));
        visited[start] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (Node next : tree[cur.to]) {
                if (visited[next.to]) {
                    continue;
                }
                visited[next.to] = true;
                int dist = cur.weight + next.weight;
                q.add(new Node(next.to, dist));

                if (dist > maxResult) {
                    maxResult = dist;
                    maxNode = next.to;
                }
            }
        }
    }
}
