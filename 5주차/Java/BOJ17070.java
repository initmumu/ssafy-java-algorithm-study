import java.io.*;
import java.util.StringTokenizer;

public class Main {

    // 0: 가로 배치
    // 1: 세로 배치
    // 2: 대각 배치
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static int cnt = 0;

    public static void dfs(int[][] house, int i, int j, int prevType, int curType, int N) {
        if (prevType == 0 && curType == 1) return;
        else if (prevType == 1 && curType == 0) return;

        int ni = i + dx[curType];
        int nj = j + dy[curType];

        // Index 체크
        if (ni < 0 || ni >= N || nj < 0 || nj >= N) return;

        // 벽 체크
        if ((curType == 0 || curType == 1) && house[ni][nj] == 1) {
            return;
        } else if ((curType == 2) && (house[i][j+1] == 1 || house[i+1][j] == 1 || house[ni][nj] == 1)) {
            return;
        }

        // 도착 체크
        if (ni == N - 1 && nj == N - 1) {
            cnt++;
            return;
        }

        for (int k = 0; k < 3; k++){
            dfs(house, ni, nj, curType, k, N);
        }
    }


    public static void solution(BufferedReader br) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(house, 0, 1, 0, 0, N);
        dfs(house, 0, 1, 0, 1, N);
        dfs(house, 0, 1, 0, 2, N);

        System.out.println(cnt);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
    }
}