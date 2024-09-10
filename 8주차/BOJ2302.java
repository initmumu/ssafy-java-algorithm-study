import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int M =  Integer.parseInt(br.readLine());
        boolean[] map = new boolean[N + 1];
        int[] dp = new int[N + 1];

        for(int m = 0; m < M; m++) {
            map[Integer.parseInt(br.readLine())] = true;
        }

        for(int i = 1; i <= N - 1; i++) {
            if(map[i] || map[i + 1]) {
                dp[i] = dp[i - 1];
                continue;
            }
            if(i - 2 < 0) {
                dp[i] = 1;
                continue;
            }
            dp[i] = dp[i - 1] + dp[i - 2] + 1;
        }
        System.out.println(dp[N - 1] + 1);
    }
}
