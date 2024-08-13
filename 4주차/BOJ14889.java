import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889 {
    static int N;
    static int[][] power;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        power = new int[N][N];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                power[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(0, 0);
        System.out.println(min);
    }

    public static void DFS(int n, int depth) {
        if (depth == N/2) {
            calculateMin();
        }
        else {
            for (int i = n; i < N; i++) {
                visited[i] = true;
                DFS(i + 1, depth + 1);
                visited[i] = false;
            }
        }
    }

    public static void calculateMin() {
        int start = 0;
        int link = 0;
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                if (visited[i] && visited[j])
                    start += power[i][j] + power[j][i];
                else if (!visited[i] && !visited[j])
                    link += power[i][j] + power[j][i];
            }
        }
        min = Math.min(min, Math.abs(start-link));
    }
}
