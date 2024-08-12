import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ1012 {
    static int N, M, K;
    static int[][] field;
    static boolean[][] visited;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static void BFS(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            visited[current[0]][current[1]] = true;
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny] && field[nx][ny] == 1) {
                    queue.add(new int[]{nx, ny});
                    visited[nx][ny] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            field = new int[N][M];
            visited = new boolean[N][M];

            for (int k = 0; k < K; k++) {
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                int Y = Integer.parseInt(st.nextToken());
                field[Y][X] = 1;
            }

            int count = 0;
            for (int n = 0; n < N; n++) {
                for (int m = 0; m < M; m++) {
                    if (field[n][m] == 1 && !visited[n][m]) {
                        BFS(n, m);
                        count++;
                    }
                }
            }
            System.out.println(count);
        }
    }
}

