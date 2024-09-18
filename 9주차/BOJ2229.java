import java.io.*;
import java.util.*;

public class BOJ2229 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] score = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            score[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            int min = 10001;    // 현재 조의 최소 점수
            int max = 0;        // 현재 조의 최대 점수
            for (int j = i; j >= 1; j--) {
                max = Math.max(max, score[j]);
                min = Math.min(min, score[j]);
                dp[i] = Math.max(dp[i], dp[j - 1] + max - min);
            }
        }

        System.out.println(dp[N]);
    }
}
