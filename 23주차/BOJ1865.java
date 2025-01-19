import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1865 {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());   // 테스트케이스의 개수
        for (int t = 0; t < TC; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());   // 지점의 수
            int M = Integer.parseInt(st.nextToken());   // 도로의 개수
            int W = Integer.parseInt(st.nextToken());   // 웜홀의 개수

            int[][] dist = new int[N + 1][N + 1];

            for (int i = 1; i <= N; i++) {
                Arrays.fill(dist[i], INF);
                dist[i][i] = 0;
            }

            // 도로의 정보
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());   // 시작 지점
                int E = Integer.parseInt(st.nextToken());   // 도착 지점
                int T = Integer.parseInt(st.nextToken());   // 걸리는 시간
                dist[S][E] = Math.min(dist[S][E], T);
                dist[E][S] = Math.min(dist[E][S], T);
            }

            // 웜홀의 정보
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int S = Integer.parseInt(st.nextToken());
                int E = Integer.parseInt(st.nextToken());
                int T = Integer.parseInt(st.nextToken());
                dist[S][E] = Math.min(dist[S][E], -T);
            }

            // 플로이드-워셜
            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (dist[i][k] != INF && dist[k][j] != INF) {
                            dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                        }
                    }
                }
            }

            boolean found = false;

            // 음의 가중치 사이클 탐지
            for (int i = 1; i <= N; i++) {
                if (dist[i][i] < 0) {
                    found = true;
                    break;
                }
            }

            if (found) {
                System.out.println("YES");
            }
            else {
                System.out.println("NO");
            }
        }
    }
}
