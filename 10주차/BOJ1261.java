import java.io.*;
import java.util.*;

public class BOJ1261 {

    static int N, M;
    static char[][] map;
    static int[][] distance;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        distance = new int[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = line.charAt(j);
                distance[i][j] = Integer.MAX_VALUE;
            }
        }

        BFS();
        System.out.println(distance[N - 1][M - 1]);
    }

    static void BFS() {
        Deque<int[]> deque = new ArrayDeque<>();
        deque.add(new int[] {0, 0});
        distance[0][0] = 0;

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                // 벽을 부수지 않고 이동
                if (map[nx][ny] == '0' && distance[nx][ny] > distance[x][y]) {
                    distance[nx][ny] = distance[x][y];
                    deque.addFirst(new int[]{nx, ny});
                }

                // 벽을 부수고 이동
                else if (map[nx][ny] == '1' && distance[nx][ny] > distance[x][y] + 1) {
                    distance[nx][ny] = distance[x][y] + 1;
                    deque.addLast(new int[]{nx, ny});
                }
            }
        }
    }
}
