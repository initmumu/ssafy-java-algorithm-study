import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7569 {
    static int H, N, M;
    static int[][][] tomato;

    static int[] dz = {-1, 1, 0, 0, 0, 0};
    static int[] dy = {0, 0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 0, -1, 1};

    static Queue<int[]> queue = new LinkedList<int[]>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        tomato = new int[H][N][M];

        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                for (int m = 0; m < M; m++) {
                    tomato[h][n][m] = Integer.parseInt(st.nextToken());
                    if (tomato[h][n][m] == 1)
                        queue.add(new int[] {h, n, m});
                }
            }
        }
        int day = BFS();

        System.out.println(day);
    }

    public static int BFS() {
        int day = 0;

        // 토마토 익히기
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean tomatTomato = false;
            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();

                for (int d = 0; d < 6; d++) {
                    int nh = current[0] + dz[d];
                    int nn = current[1] + dy[d];
                    int nm = current[2] + dx[d];

                    if (nh < 0 || nh >= H || nn < 0 || nn >= N || nm < 0 || nm >= M) continue;
                    if (tomato[nh][nn][nm] == 1) continue;
                    if (tomato[nh][nn][nm] == -1) continue;

                    tomato[nh][nn][nm] = 1;
                    queue.add(new int[]{nh, nn, nm});
                    tomatTomato = true;
                }
            }
            if (tomatTomato) day++;
        }

        // 익지 않은 토마토 남아있는지 확인
        for (int h = 0; h < H; h++) {
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (tomato[h][n][m] == 0) {
                        return -1;
                    }
                }
            }
        }

        return day;
    }
}
