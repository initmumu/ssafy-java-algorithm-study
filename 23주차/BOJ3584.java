import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ3584 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] parent = new int[N + 1];

            for (int i = 1; i < N; i++) {
                String[] edge = br.readLine().split(" ");
                int A = Integer.parseInt(edge[0]);
                int B = Integer.parseInt(edge[1]);
                parent[B] = A;
            }

            // 첫번째 노드가 루트 노드까지 방문한 노드를 저장
            Set<Integer> visited = new HashSet<>();

            String[] nodes = br.readLine().split(" ");
            int node1 = Integer.parseInt(nodes[0]);
            int node2 = Integer.parseInt(nodes[1]);

            while (node1 != 0) {
                visited.add(node1);
                node1 = parent[node1];
            }

            while (!visited.contains(node2)) {
                node2 = parent[node2];
            }

            System.out.println(node2);
        }
    }
}
