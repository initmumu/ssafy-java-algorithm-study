import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11660 {

    static int sum[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][N + 1];
        sum = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                sum[i][j] = arr[i][j] + sum[i - 1][j] + sum[i][j - 1] -sum[i - 1][j - 1];
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            sb.append(getSum(x1, y1, x2, y2)).append("\n");
        }

        System.out.println(sb);
    }

    static int getSum(int x1, int y1, int x2, int y2) {
        int result = sum[x2][y2];

        if (x1 > 1) {
            result  -= sum[x1 - 1][y2];
        }
        if (y1 > 1) {
            result -= sum[x2][y1 - 1];
        }
        if (x1 > 1 && y1 > 1) {
            result += sum[x1 - 1][y1 - 1];
        }

        return result;
    }
}
