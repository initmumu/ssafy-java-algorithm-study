import java.util.*;
import java.io.*;

public class BOJ1068 {
    static int N;
    static List<List<Integer>> tree;
    static int root;
    static int deleteNode;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        tree = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tree.add(new ArrayList<>());
        }

        root = -1;
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) {
                root = i;
            } else {
                tree.get(parent).add(i);
            }
        }

        deleteNode = Integer.parseInt(br.readLine());

        visited = new boolean[N];
        System.out.println(bfs());
    }

    static int bfs() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N];
        queue.offer(root);
        visited[root] = true;

        int leafCount = 0;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            if (node == deleteNode) continue;

            int childCount = 0;
            for (int child : tree.get(node)) {
                if (!visited[child] && child != deleteNode) {
                    visited[child] = true;
                    queue.offer(child);
                    childCount++;
                }
            }

            if (childCount == 0) {
                leafCount++;
            }
        }

        return leafCount;
    }
}
