import java.io.*;
import java.util.*;

public class BOJ2468 {
    static int N;
    static int[][] map;
    static boolean[][] visited;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    static int read() throws IOException {
    	int c, t = 0;
    	while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
    	return t;
    }

    public static void main(String[] args) throws IOException {
        N = read();
        map = new int[N][N];

        int maxHeight = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = read();
                maxHeight = Math.max(maxHeight, map[i][j]);
            }
        }

        int maxSafeAreas = 0;
        for (int h = 0; h <= maxHeight; h++) {
            visited = new boolean[N][N];
            int safeAreas = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > h) {
                        bfs(i, j, h);
                        safeAreas++;
                    }
                }
            }

            maxSafeAreas = Math.max(maxSafeAreas, safeAreas);
        }

        System.out.println(maxSafeAreas);
    }

    static void bfs(int x, int y, int height) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int cx = current[0];
            int cy = current[1];

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
                    if (!visited[nx][ny] && map[nx][ny] > height) {
                        visited[nx][ny] = true;
                        queue.add(new int[]{nx, ny});
                    }
                }
            }
        }
    }
}
