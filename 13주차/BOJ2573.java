import java.io.*;
import java.util.*;

public class BOJ2573 {

    static int N, M;
    static int[][] map;
    static int iceberg;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] != 0) {
                    iceberg++;
                }
            }
        }

        int time = 0;
        while (true) {
            if (iceberg == 0) {
                System.out.println(0);
                break;
            }
            
            if (iceberg != getIceberg()) {
                System.out.println(time);
                break;
            }

            melt();
            time++;
        }
    }

    static void melt() {
        int[][] nMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            nMap[i] = map[i].clone();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    int cnt = 0;
                    for (int d = 0; d < 4; d++) {
                        int nx = i + dx[d];
                        int ny = j + dy[d];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                            continue;
                        }

                        if (map[nx][ny] == 0) {
                            cnt++;
                        }
                    }
                    if (nMap[i][j] - cnt <= 0) {
                        iceberg--;
                        nMap[i][j] = 0;
                    }
                    else {
                        nMap[i][j] -= cnt;
                    }
//                    nMap[i][j] = Math.max(nMap[i][j] - cnt, 0);
                }
            }
        }

        map = nMap;
    }

    static int getIceberg() {
        int cnt = 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!queue.isEmpty()) {
                        int[] cur = queue.poll();
                        cnt++;

                        for (int d = 0; d < 4; d++) {
                            int nx = cur[0] + dx[d];
                            int ny = cur[1] + dy[d];

                            if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                                continue;
                            }

                            if (map[nx][ny] == 0) {
                                continue;
                            }

                            if (visited[nx][ny]) {
                                continue;
                            }

                            visited[nx][ny] = true;
                            queue.add(new int[]{nx, ny});
                        }
                    }

                    return cnt;
                }
            }
        }

        return 0;
    }
}
