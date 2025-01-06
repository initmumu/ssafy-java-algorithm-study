import java.io.*;
import java.util.*;

public class BOJ1245 {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        visited = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                graph[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (!visited[y][x] && bfs(y, x)) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    static boolean bfs(int y, int x) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y, x});
        visited[y][x] = true;

        boolean isPeak = true;

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int cy = poll[0];
            int cx = poll[1];

            for (int d = 0; d < 8; d++) {
                int ny = cy + dy[d];
                int nx = cx + dx[d];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (graph[ny][nx] > graph[cy][cx]) {
                        isPeak = false;
                    }

                    if (!visited[ny][nx] && graph[ny][nx] == graph[cy][cx]) {
                        visited[ny][nx] = true;
                        queue.add(new int[]{ny, nx});
                    }
                }
            }
        }

        return isPeak;
    }
}
