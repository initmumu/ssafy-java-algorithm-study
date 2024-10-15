import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2638 {

    static int N, M;
    static int[][] cheese;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(reader.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        cheese = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < M; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int result = meltCheese();
        System.out.println(result);
    }

    private static int meltCheese() {
        int time = 0;

        while (true) {
            visited = new boolean[N][M];
            boolean anyMelted = false;

            bfsAir();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (cheese[i][j] == 1) {
                        int contactCount = 0;

                        for (int d = 0; d < 4; d++) {
                            int ni = i + dx[d];
                            int nj = j + dy[d];

                            if (ni >= 0 && ni < N && nj >= 0 && nj < M && visited[ni][nj]) {
                                contactCount++;
                            }
                        }

                        if (contactCount >= 2) {
                            cheese[i][j] = 0;
                            anyMelted = true;
                        }
                    }
                }
            }

            if (!anyMelted) {
                break;
            }

            time++;
        }

        return time;
    }

    private static void bfsAir() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if ((i == 0 || i == N - 1 || j == 0 || j == M - 1) && cheese[i][j] == 0 && !visited[i][j]) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && !visited[nx][ny]) {
                    if (cheese[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
