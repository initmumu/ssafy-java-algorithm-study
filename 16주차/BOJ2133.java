import java.util.Scanner;

public class BOJ2133 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        
        if (N % 2 != 0) {
            System.out.println(0);
            return;
        }

        int[] dp = new int[N + 1];
        dp[0] = 1;

        for (int i = 2; i <= N; i+=2) {
            dp[i] = dp[i-2] * 3;

            for (int j = i - 4; j >= 0; j-=2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);

        sc.close();
    }
}
