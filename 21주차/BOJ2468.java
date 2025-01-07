import java.io.*;
import java.util.*;

public class BOJ2468 {
    static int N;
    static int [][] graph;
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1};

    static boolean isBound(int ny, int nx) {
        return nx >= 0 && nx < N && ny >= 0 && ny < N;
    }

    static void dfs(int[][] temp, boolean[][] visited, int y, int x) {
        visited[y][x] = true;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (!isBound(ny, nx)) continue;
            if (visited[ny][nx]) continue;
            if (temp[ny][nx] < 1) continue;
            visited[ny][nx] = true;
            dfs(temp, visited, ny, nx);
        }
    }

    static int solve(int h) {
        int [][] temp = new int[N][N];
        boolean [][] visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] <= h) {
                    temp[i][j] = 0;
                    continue;
                }
                temp[i][j] = graph[i][j];
            }
        }
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j]) continue;
                if (temp[i][j] < 1) continue;
                visited[i][j] = true;
                dfs(temp, visited, i, j);
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int [N][N];
        StringTokenizer st;
        int max = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int h = Integer.parseInt(st.nextToken());
                graph[i][j] = h;
                max = Math.max(max, h);
            }
        }
        int result = 0;
        for (int i = 0; i <= max; i++) {
            result = Math.max(result, solve(i));
        }
        System.out.println(result);
    }
}
