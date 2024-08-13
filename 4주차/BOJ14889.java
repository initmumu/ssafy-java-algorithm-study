import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[] isUsing;
    static int N;

    public static int DFS(int depth, int n) {
        if (depth == N / 2) {
            int team1 = 0, team2 = 0;

            for (int i = 0; i < N - 1; i++) {
                for (int j = i + 1; j < N; j++) {
                    if (isUsing[i] != isUsing[j])
                        continue;

                    if (isUsing[i]) {
                        team1 += map[i][j];
                        team1 += map[j][i];
                    } else {
                        team2 += map[i][j];
                        team2 += map[j][i];
                    }
                }
            }

            return Math.abs(team1 - team2);
        }

        int min = Integer.MAX_VALUE;

        for (int i = n + 1; i <= N - (N / 2 - depth); i++) {
            isUsing[i] = true;
            min = Math.min(DFS(depth + 1, i), min);
            isUsing[i] = false;
        }

        return min;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        isUsing = new boolean[N];


        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());

            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        isUsing[0] = true;
        System.out.print(DFS(1, 0));
    }

}
