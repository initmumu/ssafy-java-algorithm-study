import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503 {

    static int N, M;
    static int r, c, d;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(clean());
    }

    static int clean() {
        int x = r;
        int y = c;

        int count = 0;
        while (true) {
            // 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
            if (!visited[x][y]) {
                visited[x][y] = true;
                count++;
            }

            // 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
            boolean cleaned = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4;  // 1. 반시계 방향으로 90º 회전한다.
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (map[nx][ny] != 0 || visited[nx][ny]) {
                    continue;
                }

                // 2. 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
                x = nx;
                y = ny;
                cleaned = true;
                break;
            }

            // 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
            if (!cleaned) {
                int nd = (d + 2) % 4;
                int nx = x + dx[nd];
                int ny = y + dy[nd];

                // 1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
                if (nx >= 0 && ny >= 0 && nx < N && ny < M && map[nx][ny] == 0) {
                    x = nx;
                    y = ny;
                }
                // 2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
                else {
                    break;
                }
            }
        }

        return count;
    }
}
