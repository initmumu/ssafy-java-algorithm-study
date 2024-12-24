import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BOJ2667 {

    static int N;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        graph = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        ArrayList<Integer> sizes = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && graph[i][j] == 1) {
                    int size = dfs(i, j);
                    sizes.add(size);
                }
            }
        }

        Collections.sort(sizes);

        System.out.println(sizes.size());
        for (int size : sizes) {
            System.out.println(size);
        }
    }

    static int dfs(int x, int y) {
        visited[x][y] = true;
        int count = 1;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (!isBound(nx, ny)) {
                continue;
            }
            if (visited[nx][ny]) {
                continue;
            }
            if (graph[nx][ny] != 1) {
                continue;
            }
            count += dfs(nx, ny);

        }
        return count;
    }

    static boolean isBound(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}
