import java.io.*;
import java.util.*;

public class BOJ11567 {

    static int n, m;
    static char[][] board;

    static int r1, c1, r2, c2;

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new char[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = line.charAt(j - 1);
            }
        }

        st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());

        if (BFS()) {
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }

    static boolean BFS() {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {r1, c1});

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 1 || nx > n || ny < 1 || ny > m) {
                    continue;
                }

                if (nx == r2 && ny == c2 && board[nx][ny] == 'X') {
                    return true;
                }

                if (board[nx][ny] == 'X') {
                    continue;
                }

                queue.add(new int[] {nx, ny});
                board[nx][ny] = 'X';
            }
        }

        return false;
    }
}
