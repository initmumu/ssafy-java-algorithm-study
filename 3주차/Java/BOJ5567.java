import java.io.IOException;
import java.util.*;

public class Main {

    public static int bfs(Map<Integer, ArrayList<Integer>> g, int start) {
        List<Integer> result = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();

        queue.add(new int[]{start, 0});
        visited.add(start);

        while (!queue.isEmpty()) {
            int[] node = queue.poll();
            int currentNode = node[0];
            int depth = node[1];

            if (depth > 2) {
                continue;
            }

            if (depth > 0) {
                result.add(currentNode);
            }

            for (int neighbor : g.getOrDefault(currentNode, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(new int[]{neighbor, depth + 1});
                }
            }
        }
        return result.size();
    }

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        Map<Integer, ArrayList<Integer>> g = new HashMap<>();
        for (int i = 1; i <= N; i++) g.put(i, new ArrayList<>());

        int n1, n2;
        for (int i = 0; i < M; i++) {
            n1 = readInt(); n2 = readInt();
            g.get(n1).add(n2);
            g.get(n2).add(n1);
        }

        System.out.println(bfs(g, 1));
    }

    public static int readInt() throws IOException {
        int t = 0;
        int input;
        while ((input = System.in.read()) > 47) t = (t << 3) + (t << 1) + (input & 15);
        return t;
    }
}
