import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2302 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int[] vip = new int[M + 2];
        vip[0] = 0;
        vip[M + 1] = N + 1;
        for (int i = 1; i <= M; i++) {
            vip[i] = Integer.parseInt(br.readLine());
        }

        int result = 1;
        for (int i = 1; i < M + 2; i++) {
            result *= dp[vip[i] - (vip[i - 1] + 1)];
        }

        System.out.println(result);
    }
}
