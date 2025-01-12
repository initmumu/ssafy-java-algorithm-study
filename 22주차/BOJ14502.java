import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502 {

    static int N, M;
    static int[][] lab;
    static int[][] tempLab;
    static List<int[]> emptyArr = new ArrayList<>();
    static List<int[]> virusArr = new ArrayList<>();
    static int result;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        lab = new int[N][M];
        result = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                lab[i][j] = Integer.parseInt(st.nextToken());
                if (lab[i][j] == 0) {
                    emptyArr.add(new int[]{i, j});
                } else if (lab[i][j] == 2) {
                    virusArr.add(new int[]{i, j});
                }
            }
        }

        chooseWalls(0, 0);
        System.out.println(result);
    }

    static void chooseWalls(int depth, int s) {
        if (depth == 3) {
            tempLab = copyLab();
            spreadVirus();
            result = Math.max(result, calculateSafeArea());
            return;
        }

        for (int i = s; i < emptyArr.size(); i++) {
            int[] space = emptyArr.get(i);
            lab[space[0]][space[1]] = 1;
            chooseWalls(depth + 1, i + 1);
            lab[space[0]][space[1]] = 0;
        }
    }

    static int[][] copyLab() {
        int[][] copy = new int[N][M];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                copy[y][x] = lab[y][x];
            }
        }
        return copy;
    }

    static void spreadVirus() {
        Queue<int[]> queue = new LinkedList<>();
        for (int[] virus : virusArr) {
            queue.add(virus);
        }

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int y = current[0];
            int x = current[1];

            for (int i = 0; i < 4; i++) {
                int ny = y + dy[i];
                int nx = x + dx[i];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M && tempLab[ny][nx] == 0) {
                    tempLab[ny][nx] = 2;
                    queue.add(new int[]{ny, nx});
                }
            }
        }
    }

    static int calculateSafeArea() {
        int safeArea = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (tempLab[y][x] == 0) {
                    safeArea++;
                }
            }
        }
        return safeArea;
    }
}
