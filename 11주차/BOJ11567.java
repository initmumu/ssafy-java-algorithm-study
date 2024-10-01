import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Point {
    int y, x;

    public Point(int y, int x) {
        this.y = y;
        this.x = x;
    }
}

public class BOJ11567 {

    public static int N, M, startY, startX, endY, endX;
    public static char[][] graph;
    public static boolean[][] visited;
    public static int[] dy = {-1, 0, 1, 0};
    public static int[] dx = {0, 1, 0, -1};

    public static boolean bfs() {
        Deque<Point> queue = new ArrayDeque<>();
        queue.add(new Point(startY, startX));
        visited[startY][startX] = true;

        while (!queue.isEmpty()) {
            Point point = queue.poll();
            int y = point.y;
            int x = point.x;

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (checkBound(ny, nx)) continue;

                if (graph[ny][nx] == 'X' || visited[ny][nx]) {
                    if (ny == endY && nx == endX) {
                        return true;
                    }
                    continue;
                }

                visited[ny][nx] = true;
                queue.add(new Point(ny, nx));
            }
        }
        return false;
    }

    public static boolean checkBound(int ny, int nx) {
        return ny < 0 || ny >= N || nx < 0 || nx >= M;
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        st = new StringTokenizer(br.readLine());
        startY = Integer.parseInt(st.nextToken()) - 1;
        startX = Integer.parseInt(st.nextToken()) - 1;

        st = new StringTokenizer(br.readLine());
        endY = Integer.parseInt(st.nextToken()) - 1;
        endX = Integer.parseInt(st.nextToken()) - 1;

        if (bfs()) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
