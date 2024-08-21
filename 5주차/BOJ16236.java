import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16236 {
    static int N;
    static int[][] space;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};

    static int sharkX, sharkY;
    static int sharkSize = 2;
    static int eatCount = 0;
    static int time = 0;

    static boolean targetFind;

    static Queue<int[]> shark = new LinkedList<>();
    static Queue<int[]> target = new LinkedList<>();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        space = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                space[i][j] = Integer.parseInt(st.nextToken());
                if (space[i][j] == 9) {
                    shark.add(new int[]{i, j});
                    visited[i][j] = true;
                    space[i][j] = 0;
                }
            }
        }

        findTime();
        System.out.println(time);
    }

    static void findTime() {
        while (true) {
            // 상어가 더이상 움직일 수 있는 공간이 없다면 break;
            if(!move()) break;

            // 먹을 수 있는 물고기를 발견 했다면 우선순위가 높은 물고기를 찾고
            // 그 물고기를 먹음
            if(targetFind) {
                int[] targetPos = findTarget();
                int targetX = targetPos[0];
                int targetY = targetPos[1];

                eatTarget(targetX, targetY);
            }
        }
    }

    // 상어가 같은 시간동안 갈 수 있는 이동 경로를 모두 체크
    static boolean move() {
        int size = shark.size();
        targetFind = false;
        for (int i = 0; i < size; i++) {
            int[] sharkPos = shark.poll();
            sharkX = sharkPos[0];
            sharkY = sharkPos[1];

            for (int d = 0; d < 4; d++) {
                int nx = sharkX + dx[d];
                int ny = sharkY + dy[d];
                // 공간을 벗어나거나 이미 방문한 공간이면 체크하지 않음
                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                    continue;
                }
                // 빈 칸이 아니고 칸에 있는 물고기가 상어의 크기보다 작으면 먹이를 찾은 것
                if (space[nx][ny] != 0 && space[nx][ny] < sharkSize) {
                    target.add(new int[] {nx, ny});
                    targetFind = true;
                }
                shark.add(new int[] {nx, ny});
                visited[nx][ny] = true;
            }
        }
        // 사이클을 돌았는데 큐가 비어있다면 이동할 수 있는 공간이 없는것
        return !shark.isEmpty();
    }

    // 우선순위가 높은 물고기를 찾아 그 위치를 반환
    static int[] findTarget() {
        int targetX = Integer.MAX_VALUE, targetY = Integer.MAX_VALUE;
        while (!target.isEmpty()) {
            int[] targetPos = target.poll();
            if (targetPos[0] < targetX) {
                targetX = targetPos[0];
                targetY = targetPos[1];
            }
            if (targetPos[0] == targetX && targetPos[1] < targetY) {
                targetX = targetPos[0];
                targetY = targetPos[1];
            }
        }

        return new int[] {targetX, targetY};
    }

    static void eatTarget(int targetX, int targetY) {
        int distance = Math.abs(targetX - sharkX) + Math.abs(targetY - sharkY);
        time += distance;

        sharkX = targetX;
        sharkY = targetY;
        shark.clear();
        shark.add(new int[] {sharkX, sharkY});

        visited = new boolean[N][N];
        visited[sharkX][sharkY] = true;

        eatCount++;
        if (eatCount == sharkSize) {
            eatCount = 0;
            sharkSize++;
        }
    }
}
