import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ25682 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        char[][] graph = new char[N][M];

        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        int[][] wGraph = new int[N + 1][M + 1];
        int[][] bGraph = new int[N + 1][M + 1];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char expectedW = ((i + j) % 2 == 0) ? 'W' : 'B';
                char expectedB = ((i + j) % 2 == 0) ? 'B' : 'W';

                wGraph[i + 1][j + 1] = wGraph[i][j + 1] + wGraph[i + 1][j] - wGraph[i][j];
                bGraph[i + 1][j + 1] = bGraph[i][j + 1] + bGraph[i + 1][j] - bGraph[i][j];

                if (graph[i][j] != expectedW) {
                    wGraph[i + 1][j + 1]++;
                }
                if (graph[i][j] != expectedB) {
                    bGraph[i + 1][j + 1]++;
                }
            }
        }

        int min_result = Integer.MAX_VALUE;

        for (int i = 0; i <= N - K; i++) {
            for (int j = 0; j <= M - K; j++) {
                int wCount = wGraph[i + K][j + K] - wGraph[i][j + K] - wGraph[i + K][j] + wGraph[i][j];
                int bCount = bGraph[i + K][j + K] - bGraph[i][j + K] - bGraph[i + K][j] + bGraph[i][j];

                int min = Math.min(wCount, bCount);
                min_result = Math.min(min_result, min);
            }
        }

        System.out.println(min_result);
    }
}
