import java.io.*;
import java.util.*;

public class Main {
    public static void bfs(int sx, int sy, int tx, int ty, int I) {
        int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};
        int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};

        boolean[][] vst = new boolean[I][I];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx, sy, 0});
        vst[sx][sy] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();

            if (pos[0] == tx && pos[1] == ty) {
                System.out.println(pos[2]);
                return;
            }

            for (int d = 0; d < 8; d++) {
                int nx = pos[0] + dx[d];
                int ny = pos[1] + dy[d];
                int depth = pos[2];

                if (-1 >= nx || nx >= I || -1 >= ny || ny >= I ) {
                    continue;
                }

                if (!vst[nx][ny]) {
                    vst[nx][ny] = true;
                    queue.add(new int[] {nx, ny, depth+1});
                }
            }
        }
    }

    public static void solution(BufferedReader br, int tc) throws IOException {
        int I = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int tx = Integer.parseInt(st.nextToken());
        int ty = Integer.parseInt(st.nextToken());

        bfs(sx, sy, tx, ty, I);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            solution(br, tc);
        }
    }

}