import java.io.*;

public class Main {

    static int N;
    static int[][] grid;
    static int totalSandOut = 0;

    // 토네이도 모래 퍼지는 비율 (x, y, 비율)
    static int[][][] spreadPattern = {
            // 왼쪽 방향
            {{-2, 0, 2}, {-1, -1, 10}, {-1, 0, 7}, {-1, 1, 1}, {0, -2, 5}, {1, -1, 10}, {1, 0, 7}, {1, 1, 1}, {2, 0, 2}},
            // 아래쪽 방향
            {{-1, -1, 1}, {-1, 1, 1}, {0, -2, 2}, {0, -1, 7}, {0, 1, 7}, {0, 2, 2}, {1, -1, 10}, {1, 1, 10}, {2, 0, 5}},
            // 오른쪽 방향
            {{-2, 0, 2}, {-1, -1, 1}, {-1, 0, 7}, {-1, 1, 10}, {0, 2, 5}, {1, -1, 1}, {1, 0, 7}, {1, 1, 10}, {2, 0, 2}},
            // 위쪽 방향
            {{-2, 0, 5}, {-1, -1, 10}, {-1, 1, 10}, {0, -2, 2}, {0, -1, 7}, {0, 1, 7}, {0, 2, 2}, {1, -1, 1}, {1, 1, 1}}
    };

    // 토네이도가 이동하는 방향 (왼, 아래, 오, 위 순서)
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        N = read();
        grid = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = read();
            }
        }

        int x = N / 2; // 토네이도의 시작 위치
        int y = N / 2;
        int direction = 0; // 초기 방향은 왼쪽

        int moveLength = 1; // 이동 길이
        boolean finish = false;

        while (!finish) {
            length: for (int i = 0; i < 2; i++) {
                for (int j = 0; j < moveLength; j++) {
                    x += dx[direction];
                    y += dy[direction];

                    spreadSand(x, y, direction);

                    if (x == 0 && y == 0) {
                        finish = true;
                        break length;
                    }
                }
                direction = (direction + 1) % 4; // 방향을 시계방향으로 회전
            }
            moveLength++;
        }

        System.out.println(totalSandOut);
    }

    static void spreadSand(int x, int y, int direction) {
        int totalSand = grid[x][y];
        grid[x][y] = 0;

        int spreadSum = 0;
        for (int[] pattern : spreadPattern[direction]) {
            int nx = x + pattern[0];
            int ny = y + pattern[1];
            int spread = totalSand * pattern[2] / 100;
            spreadSum += spread;

            if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                grid[nx][ny] += spread;
            } else {
                totalSandOut += spread;
            }
        }

        // 남은 모래를 '알파'로 처리
        int nx = x + dx[direction];
        int ny = y + dy[direction];
        int alphaSand = totalSand - spreadSum;

        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            grid[nx][ny] += alphaSand;
        } else {
            totalSandOut += alphaSand;
        }
    }

    public static int read() throws IOException {
        int c, t = 0;
        while( (c = System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}
