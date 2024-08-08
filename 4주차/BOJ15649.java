import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15649 {

    static int N, M;
    static int[] permutation;
    static boolean[] visited;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        permutation = new int[M];
        visited = new boolean[N + 1];

        System.out.println(permutations(0));
    }

    static StringBuilder permutations (int depth) {
        if (depth == M) {
            for (int p: permutation) sb.append(p + " ");
            sb.append("\n");
        }
        else {
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    permutation[depth] = i;
                    permutations(depth + 1);
                    visited[i] = false;
                }
            }
        }
        return sb;
    }
}
