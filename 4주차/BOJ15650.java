import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15650 {

    static int N, M;
    static int[] combination;
    static boolean[] visited;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        combination = new int[M];
        visited = new boolean[N + 1];

        System.out.println(combinations(0, 1));
    }

    static StringBuilder combinations (int depth, int cur) {
        if (depth == M) {
            for (int p: combination) sb.append(p + " ");
            sb.append("\n");
        }
        else {
            for (int i = cur; i <= N; i++) {
                combination[depth] = i;
                combinations(depth + 1, i + 1);
            }
        }
        return sb;
    }
}
