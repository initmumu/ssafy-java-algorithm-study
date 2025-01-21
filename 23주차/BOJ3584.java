import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3584 {

    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            parent = new int[N + 1];
            visited = new boolean[N + 1];

            for (int i = 0; i < N - 1; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                parent[B] = A; // B의 부모는 A
            }

            // 두 노드 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            sb.append(findCommon(node1, node2)).append("\n");
        }

        System.out.print(sb.toString());
    }

    static int findCommon(int node1, int node2) {
        boolean[] isParent = new boolean[parent.length];

        // node1의 모든 조상을 추적
        while (node1 != 0) {
            isParent[node1] = true;
            node1 = parent[node1];
        }

        // node2의 조상을 탐색하며 처음 만나는 공통 조상을 반환
        while (node2 != 0) {
            if (isParent[node2]) {
                return node2;
            }
            node2 = parent[node2];
        }

        return -1;
    }
}
