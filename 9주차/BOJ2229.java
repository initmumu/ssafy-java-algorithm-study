import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] a = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 2; i <= N; i++) {
            int minv = a[i], maxv = a[i];
            for (int j = i - 1; j >= 0; j--) {
                maxv = Math.max(a[j + 1], maxv);
                minv = Math.min(a[j + 1], minv);
                dp[i] = Math.max(dp[i], dp[j] + maxv - minv);
            }
        }
        System.out.println(dp[N]);
    }
}
