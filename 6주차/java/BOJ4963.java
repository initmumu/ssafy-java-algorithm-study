import java.io.*;
import java.util.*;

public class Main {
    public static int cnt = 0, N, M;
    public static int[][] world;
    public static boolean[][] visited;
    public static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    public static int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void dfs(int x, int y) {
        for (int idx = 0; idx < 8; idx++) {
            int nx = x + dx[idx];
            int ny = y + dy[idx];

            if (isValid(nx, ny) && !visited[nx][ny] && world[nx][ny] == 1) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    public static boolean isValid(int x, int y) {
        return -1 < x && x < M && -1 < y && y < N;
    }

    public static boolean solution(BufferedReader br, StringBuilder sb) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (N == 0 && M == 0) return true;

        world = new int[M][N];
        visited = new boolean[M][N];
        cnt = 0;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                world[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && world[i][j] == 1) {
                    visited[i][j] = true;
                    dfs(i, j);
                    cnt++;
                }
            }
        }

        sb.append(String.format("%d\n", cnt));
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true){
            if (solution(br, sb)) break;
        }

        System.out.println(sb);

    }
}