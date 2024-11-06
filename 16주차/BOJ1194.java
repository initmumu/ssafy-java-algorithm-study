import java.io.*;
import java.util.*;

public class BOJ_1194 {

    static int N, M;
    static char[][] maze;
    static boolean[][][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new char[N][M];
        visited = new boolean[N][M][1 << 6];

        int sX = 0, sY = 0;

        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == '0') {
                    sX = i;
                    sY = j;
                }
            }
        }

        System.out.println(BFS(sX, sY));
    }

    static int BFS(int sX, int sY) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sX, sY, 0});
        visited[sX][sY][0] = true;

        int moveCnt = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int x = cur[0];
                int y = cur[1];
                int keys = cur[2];

                for (int d = 0; d < 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];
                    int nKey = keys;

                    if (nx < 0 || nx >= N || ny < 0 || ny >= M || maze[nx][ny] == '#') {
                        continue;
                    }

                    // 출구 도달한 경우
                    if (maze[nx][ny] == '1') {
                        return moveCnt;
                    }

                    // 문을 만난 경우
                    if ('A' <= maze[nx][ny] && maze[nx][ny] <= 'F') {
                        int door = maze[nx][ny] - 'A';

                        // 열쇠가 없으면 이동할 수 없음
                        if ((keys & (1 << door)) == 0) {
                            continue;
                        }
                    }

                    // 열쇠를 만난 경우
                    if ('a' <= maze[nx][ny] && maze[nx][ny] <= 'f') {
                        int key = maze[nx][ny] - 'a';
                        nKey |= (1 << key);
                    }

                    if (visited[nx][ny][nKey]) {
                        continue;
                    }

                    queue.add(new int[] {nx, ny, nKey});
                    visited[nx][ny][nKey] = true;
                }
            }

            moveCnt++;
        }

        return -1;
    }
}
