import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ16954 {

    static char[][] map = new char[8][8];
    static int[] dx = {-1, -1, -1, 0, 1, 1, 1, 0};
    static int[] dy = {-1, 0, 1, 1, 1, 0, -1, -1};

    static boolean arrival = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 8; i++) {
            map[i] = br.readLine().toCharArray();
        }
        map[7][0] = '@';

        while (true) {
            BFS();
            if (!check()) {
                System.out.println(0);
                break;
            }
            if (arrival) {
                System.out.println(1);
                break;
            }
            moveWall();
            // printMap();
        }
    }

    static void printMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static void BFS() {
        boolean[][] visited = new boolean[8][8];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '@') {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0];
            int y = pos[1];

            for (int d = 0; d < 8; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8) {
                    continue;
                }
                if (visited[nx][ny] || map[nx][ny] == '@') {
                    continue;
                }
                if (map[nx][ny] == '.') {
                    if (nx == 0 && ny == 7) {
                        arrival = true;
                        return;
                    }
                    map[nx][ny] = '@';
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static void moveWall() {
        for (int i = 7; i > 0; i--) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '#') {
                    map[i][j] = '.';
                }
                if (map[i - 1][j] == '#') {
                    map[i][j] = '#';
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            map[0][i] = '.';
        }
    }

    static boolean check() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (map[i][j] == '@') {
                    return true;
                }
            }
        }

        return false;
    }
}
