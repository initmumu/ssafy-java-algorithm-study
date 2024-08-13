import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int M;

    public static void DFS(int n, int depth, boolean[] visited, String str) {
        if (++depth == M) {
            System.out.println(str + n);
            return;
        }
        visited[n - 1] = true;
        for (int i = 1; i <= N; i++) {
            if (visited[i - 1])
                continue;

            boolean[] newV = visited.clone();
            DFS(i, depth, newV, str + n + " ");
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= N; i++) {
            boolean[] visited = new boolean[N];
            DFS(i, 0, visited, "");
        }
    }

}
