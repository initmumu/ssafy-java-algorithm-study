import java.io.*;
import java.util.*;

public class BOJ21278 {

    static final int INF = 1000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    dist[i][j] = 0;
                }
                else {
                    dist[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            dist[a][b] = 1;
            dist[b][a] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        int minDistance = INF;
        int chicken1 = -1;
        int chicken2 = -1;

        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                int totalDistance = 0;
                
                for (int k = 1; k <= N; k++) {
                    totalDistance += Math.min(dist[k][i], dist[k][j]) * 2;
                }

                if (totalDistance < minDistance) {
                    minDistance = totalDistance;
                    chicken1 = i;
                    chicken2 = j;
                }
            }
        }

        System.out.println(chicken1 + " " + chicken2 + " " + minDistance);
    }
}
