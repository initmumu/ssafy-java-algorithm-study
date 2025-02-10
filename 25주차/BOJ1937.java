import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1937 {

    static int N;
    static int[][] dp;
    static int[][] forest;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st;

        dp = new int[N][N];
        forest = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                forest[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxMove = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxMove = Math.max(maxMove, DFS(i, j));
            }
        }

        System.out.println(maxMove);
    }

    static int DFS(int x, int y) {
        if (dp[x][y] != 0) {
            return dp[x][y];
        }

        dp[x][y] = 1;

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];

            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                continue;
            }
            if (forest[nx][ny] <= forest[x][y]) {
                continue;
            }

            dp[x][y] = Math.max(dp[x][y], DFS(nx, ny) + 1);
        }

        return dp[x][y];
    }

    static int BFS(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        queue.add(new int[]{sx, sy});
        visited[sx][sy] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;

            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                        continue;
                    }
                    if (visited[nx][ny] || forest[nx][ny] <= forest[x][y]) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }

        return count;
    }
}
