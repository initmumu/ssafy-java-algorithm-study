import java.io.IOException;

class Cloud {
    public int x, y;
    int[][] dirs = new int[][] {{-1, 0}, {-1, -1}, {0, -1}, {1, -1}, {1, 0}, {1, 1}, {0, 1}, {-1, 1}};

    public Cloud(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void MoveCloud(int N, int direction, int distance) {
        x += dirs[direction - 1][0] * distance;
        y += dirs[direction - 1][1] * distance;

        x %= N;
        y %= N;

        if (x < 0) {
            x = N + x;
        }

        if (y < 0) {
            y = N + y;
        }
    }
}


public class Main {
    static int N;
    static int[][] A;
    static Cloud[] clouds;
    static int cloudCount;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        N = readInt();
        int M = readInt();
        A = new int[N][N];
        visited = new boolean[N][N];
        int direction, distance;
        clouds = new Cloud[N * N];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                A[y][x] = readInt();
            }
        }

        // 초기 구름 세팅
        clouds[0] = new Cloud(0, N - 1);
        clouds[1] = new Cloud(1, N - 1);
        clouds[2] = new Cloud(0, N - 2);
        clouds[3] = new Cloud(1, N - 2);

        cloudCount = 4;

        // 핵심
        for (int i = 0; i < M; i++) {
            direction = readInt();
            distance = readInt();

            MoveCloudsAndMakeItRain(direction, distance);
            WaterCopyBug();
            MakeClouds();
        }

        int result = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                result += A[y][x];
            }
        }
        System.out.print(result);
    }


    public static int readInt() throws IOException {
        int input;
        int result = 0;
        while ((input = System.in.read()) > 47) {
            result = (result << 3) + (result << 1) + (input & 15);
        }
        return result;
    }

    public static boolean isOutOfMap(int N, int x, int y) {
        return x < 0 || x >= N || y < 0 || y >= N;
    }


    public static void MoveCloudsAndMakeItRain(int direction, int distance){
        for (int q = 0; q < cloudCount; q++) {
            Cloud c = clouds[q];
            c.MoveCloud(N, direction, distance);
            visited[c.y][c.x] = true;
            A[c.y][c.x]++;
        }
    }

    public static void WaterCopyBug(){
        int[][] dirs = new int[][] {{-1, -1}, {-1, 1}, {1, 1}, {1, -1}};

        for (int q = 0; q < cloudCount; q++) {
            Cloud c = clouds[q];
            int increase = 0;
            for (int j = 0; j < 4; j++) {
                int newX = c.x + dirs[j][0];
                int newY = c.y + dirs[j][1];

                if (isOutOfMap(N, newX, newY)) {
                    continue;
                }
                if (A[newY][newX] > 0)
                    increase++;
            }

            A[c.y][c.x] += increase;
        }

        cloudCount = 0;
    }

    public static void MakeClouds(){
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (visited[y][x] || A[y][x] < 2)
                    continue;
                A[y][x] -= 2;
                clouds[cloudCount++] = new Cloud(x, y);
            }
        }
        visited = new boolean[N][N];
    }
}
