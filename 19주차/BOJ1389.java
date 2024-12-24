import java.io.*;
import java.util.*;

public class BOJ1389 {

    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] relation  = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) {
                    relation[i][j] = 0;
                } else {
                    relation[i][j] = INF;
                }
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            relation[a][b] = 1;
            relation[b][a] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (relation[i][k] != INF && relation[k][j] != INF) {
                        relation[i][j] = Math.min(relation[i][j], relation[i][k] + relation[k][j]);
                    }
                }
            }
        }

        int min = INF;
        int[] kevinBacon = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int sum = 0;
            for (int j = 1; j <= N; j++) {
                if (relation[i][j] != INF) {
                    sum += relation[i][j];
                }
            }
            min = Math.min(min, sum);
            kevinBacon[i] = sum;
        }

        for (int i = 1; i <= N; i++) {
            if (kevinBacon[i] == min) {
                System.out.println(i);
                break;
            }
        }
    }
}
