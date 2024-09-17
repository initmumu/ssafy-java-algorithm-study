import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2229 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] scores = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            scores[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            int maxScore = scores[i];
            int minScore = scores[i];

            for (int j = i; j >= 1; j--) {
                maxScore = Math.max(maxScore, scores[j]);
                minScore = Math.min(minScore, scores[j]);

                dp[i] = Math.max(dp[i], dp[j - 1] + (maxScore - minScore));
            }
        }

        System.out.println(dp[N]);
    }
}
