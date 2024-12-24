import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14940 {

    static int n, m;
    static int[][] graph;
    static int[][] dist;
    static boolean[][] visited;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        dist = new int[n][m];
        visited = new boolean[n][m];

        int startY = 0;
        int startX = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = -1;

                if (graph[i][j] == 2) {
                    startY = i;
                    startX = j;
                }
            }
        }

        bfs(startY, startX);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (graph[i][j] == 0) {
                    sb.append(0).append(" ");
                } else if (dist[i][j] == -1 && graph[i][j] == 1) {
                    sb.append(-1).append(" ");
                } else {
                    sb.append(dist[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    static void bfs(int startY, int startX) {
        Queue<int[]> queue = new LinkedList<>();

        visited[startY][startX] = true;
        dist[startY][startX] = 0;
        queue.offer(new int[]{startY, startX});

        while (!queue.isEmpty()) {
            int[] poll = queue.poll();
            int y = poll[0];
            int x = poll[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < n && nx >= 0 && nx < m) {
                    if (!visited[ny][nx] && graph[ny][nx] == 1) {
                        visited[ny][nx] = true;
                        dist[ny][nx] = dist[y][x] + 1;
                        queue.offer(new int[]{ny, nx});
                    }
                }
            }
        }
    }
}
