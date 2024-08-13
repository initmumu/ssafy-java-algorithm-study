import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {

    static int N;
    static int[][] graph;
    static int result = Integer.MAX_VALUE;
    static boolean[] visited;

    public static void dfs(int n, int s, int[] lst, int lstSize) {
        if (n == N / 2) {
            boolean[] used = new boolean[N + 1];
            for (int i = 0; i < lstSize; i++) {
                used[lst[i]] = true;
            }
            int[] lst2 = new int[N / 2];
            int idx = 0;
            for (int i = 1; i <= N; i++) {
                if (!used[i]) {
                    lst2[idx++] = i;
                }
            }

            int sm = 0;
            for (int i = 0; i < lstSize; i++) {
                for (int j = 0; j < lstSize; j++) {
                    if (i != j) {
                        sm += graph[lst[i]][lst[j]];
                    }
                }
            }

            int sm2 = 0;
            for (int i = 0; i < N / 2; i++) {
                for (int j = 0; j < N / 2; j++) {
                    if (i != j) {
                        sm2 += graph[lst2[i]][lst2[j]];
                    }
                }
            }

            result = Math.min(result, Math.abs(sm - sm2));
            return;
        }

        for (int j = s; j <= N; j++) {
            if (!visited[j]) {
                visited[j] = true;
                int[] newLst = new int[n + 1];
                System.arraycopy(lst, 0, newLst, 0, n);
                newLst[n] = j;
                dfs(n + 1, j, newLst, n + 1);
                visited[j] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1];
        StringTokenizer st;
        for (int i = 1; i <= N; i++) {
        	st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, new int[N], 0);
        System.out.println(result);
    }
}
