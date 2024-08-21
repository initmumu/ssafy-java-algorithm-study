import java.io.*;
import java.util.*;

public class Main {
    static int min = 2100000000;
    public static void perm(int idx, int N, int[] selected, boolean[] vst, int[][] cmd, int[][] arr) {
        if (idx == N) {
            int[][] arr2 = new int[arr.length][];
            for (int i = 0; i < arr.length; i++) {
                arr2[i] = Arrays.copyOf(arr[i], arr[i].length);
            }

            for (int i : selected) {
                rotateArray(arr2, cmd[i][0], cmd[i][1], cmd[i][2]);
            }


            int det = calcDet(arr2);
            min = Math.min(min, det);
        }

        for (int i = 0; i < N; i++) {
            if (!vst[i]) {
                selected[idx] = i;
                vst[i] = true;
                perm(idx + 1, N, selected, vst, cmd, arr);
                vst[i] = false;
            }
        }

    }

    public static int calcDet(int[][] arr) {
        int min = Integer.MAX_VALUE;
        for (int[] row: arr) {
            int temp = 0;
            for (int v : row) {
                temp += v;
            }
            min = Math.min(min, temp);
        }
        return min;
    }

    public static void rotateArray(int[][] arr, int r, int c, int s) {
        r -= 1; c -= 1;
        for (int i = 1; i <= s; i++) {
            Deque<Integer> queue = new LinkedList<>();

            for (int j = -i; j <= i; j++) {
                queue.add(arr[r-i][c+j]);
            }

            for (int j = -i+1; j < i; j++) {
                queue.add(arr[r+j][c+i]);
            }

            for (int j = i; j >= -i; j--) {
                queue.add(arr[r+i][c+j]);
            }

            for (int j = i-1; j > -i; j--) {
                queue.add(arr[r+j][c-i]);
            }
            queue.addFirst(queue.removeLast());
            for (int j = -i; j <= i; j++) {
                arr[r-i][c+j] = queue.poll();
            }

            for (int j = -i+1; j < i; j++) {
                arr[r+j][c+i] = queue.poll();
            }

            for (int j = i; j >= -i; j--) {
                arr[r+i][c+j] = queue.poll();
            }

            for (int j = i-1; j > -i; j--) {
                arr[r+j][c-i] = queue.poll();
            }
        }
    }

    public static void solution(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 입력
        int[][] arr = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) arr[i][j] = Integer.parseInt(st.nextToken());
        }

        int[][] cmd = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) cmd[i][j] = Integer.parseInt(st.nextToken());
        }

        perm(0, K, new int[K], new boolean[K], cmd, arr);

        System.out.println(min);



    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        solution(br);
    }
}