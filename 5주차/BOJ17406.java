import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17406 {
    static int N, M, K;
    static int[][] arr;
    static int[][] rcs;

    static boolean[] visited;
    static int[] permutation;

    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        rcs = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            rcs[i][0] = Integer.parseInt(st.nextToken()) - 1;
            rcs[i][1] = Integer.parseInt(st.nextToken()) - 1;
            rcs[i][2] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[K];
        permutation = new int[K];

        DFS(0);

        System.out.println(min);
    }

    static void DFS(int depth){
        // 순열 다 생성 되면, 순서대로 배열 회전시키고 최솟값 찾기
        if (depth == K) {
            int[][] tempArr = new int[N][M];
            for (int i = 0; i < N; i++) {
                tempArr[i] = arr[i].clone();
            }

            for (int i = 0; i < K; i++) {
                tempArr = rotate(tempArr, rcs[permutation[i]]);
            }

            min = Math.min(min, calculateMinSum(tempArr));
            return;
        }

        for (int i = 0; i < K; i++) {
            // 방문 체크후 DFS
            if (!visited[i]) {
                visited[i] = true;
                permutation[depth] = i;
                DFS(depth + 1);
                visited[i] = false;
            }
        }
    }

    static int[][] rotate(int[][] arr, int[] rcs) {
        int c = rcs[0];
        int r = rcs[1];
        int s = rcs[2];

        int[][] tempArr = new int[N][M];
        for (int i = 0; i < N; i++) {
            tempArr[i] = arr[i].clone();
        }

        for (int i = s; i > 0; i--) {
            // 위쪽 돌리기
            for (int row = r-i; row < r+i; row++) {
                tempArr[c - i][row + 1] = arr[c - i][row];
            }

            // 오른쪽 돌리기
            for (int col = c-i; col < c+i; col++) {
                tempArr[col + 1][r + i] = arr[col][r + i];
            }

            // 아래쪽 돌리기
            for (int row = r+i; row > r-i; row--) {
                tempArr[c + i][row - 1] = arr[c + i][row];
            }

            // 왼쪽 돌리기
            for (int col = c+i; col > c-i; col--) {
                tempArr[col - 1][r - i]= arr[col][r - i];
            }
        }

        return tempArr;
    }

    static int calculateMinSum(int[][] arr) {
        int minSum = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            int rSum = 0;
            for (int j = 0; j < M; j++) {
                rSum += arr[i][j];
            }
            minSum = Math.min(minSum, rSum);
        }
        return minSum;
    }
}
