import java.io.*;
import java.util.*;

public class Main {
    static int R, C, T;
    static int[][] map;
    static int top;
    static int bottom;

    public static class Node {
        public int y;
        public int x;
        public int munzi;

        public Node(int y, int x, int munzi) {
            this.y = y;
            this.x = x;
            this.munzi = munzi;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                top = i;
                bottom = i + 1;
                break;
            }
        }

        for (int i = 0; i < T; i++) {
            HwackSan();
            GongChung();
        }

        int result = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0)
                    result += map[i][j];
            }
        }
        System.out.print(result);
    }

    public static int[][] dirs = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

    public static void HwackSan() {
        int[][] temp = new int[R][C];

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0) {
                    int spreadAmount = map[i][j] / 5;
                    int spreadCount = 0;

                    for (int[] dir : dirs) {
                        int ny = i + dir[0];
                        int nx = j + dir[1];

                        if (IsOutOfMap(ny, nx) || (ny == top && nx == 0) || (ny == bottom && nx == 0))
                            continue;

                        temp[ny][nx] += spreadAmount;
                        spreadCount++;
                    }

                    temp[i][j] += map[i][j] - spreadAmount * spreadCount;
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    public static void GongChung() {

        for (int i = top - 1; i > 0; i--)
            map[i][0] = map[i - 1][0]; // 왼쪽 세로
        for (int i = 0; i < C - 1; i++)
            map[0][i] = map[0][i + 1]; // 위쪽 가로
        for (int i = 0; i < top; i++)
            map[i][C - 1] = map[i + 1][C - 1]; // 오른쪽 세로
        for (int i = C - 1; i > 1; i--)
            map[top][i] = map[top][i - 1]; // 아래쪽 가로
        map[top][1] = 0;

        for (int i = bottom + 1; i < R - 1; i++)
            map[i][0] = map[i + 1][0]; // 왼쪽 세로
        for (int i = 0; i < C - 1; i++)
            map[R - 1][i] = map[R - 1][i + 1]; // 아래쪽 가로
        for (int i = R - 1; i > bottom; i--)
            map[i][C - 1] = map[i - 1][C - 1]; // 오른쪽 세로
        for (int i = C - 1; i > 1; i--)
            map[bottom][i] = map[bottom][i - 1]; // 위쪽 가로
        map[bottom][1] = 0;
    }

    public static boolean IsOutOfMap(int y, int x) {
        return y < 0 || y >= R || x < 0 || x >= C;
    }
}