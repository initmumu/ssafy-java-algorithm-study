import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[][] result;
    static coord target;

    public static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static class coord {
        public int x, y;
        public coord(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void bfs() {
        Queue<coord> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N + 2][M + 2];

        queue.add(target);
        visited[target.y][target.x] = true;
        result[target.y - 1][target.x - 1] = 0;

        while (!queue.isEmpty()) {
            coord cur = queue.poll();

            for (int[] dir : dirs) {
                int ny = cur.y + dir[0];
                int nx = cur.x + dir[1];

                if (ny <= 0 || ny > N || nx <= 0 || nx > M || visited[ny][nx] || map[ny][nx] == 0) {
                    continue;
                }

                visited[ny][nx] = true;
                result[ny - 1][nx - 1] = result[cur.y - 1][cur.x - 1] + 1;
                queue.add(new coord(ny, nx));
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    result[i - 1][j - 1] = -1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 2][M + 2];
        result = new int[N][M];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                int n = Integer.parseInt(st.nextToken());
                if (n == 2) {
                    target = new coord(i, j);
                }
                map[i][j] = n;
            }
        }

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(result[i][j]).append(' ');
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }
}
