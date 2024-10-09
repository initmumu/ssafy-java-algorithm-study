import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] blocks;
    static int[][] dp;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        blocks = new int[N][4];
        max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            blocks[i][0] = Integer.parseInt(st.nextToken());
            blocks[i][1] = Integer.parseInt(st.nextToken());
            blocks[i][2] = Integer.parseInt(st.nextToken());
            blocks[i][3] = i + 1;
        }

        Arrays.sort(blocks, (o1, o2) -> o2[0] - o1[0]);

        for(int i = 0; i < N; i++) {
            int max = blocks[i][1];
            int temp = i;
            for(int j = 0; j < i; j++) {
                if(blocks[j][2] >= blocks[i][2]) {
                    if(max <= dp[j][0] + blocks[i][1]) {
                        max = dp[j][0] + blocks[i][1];
                        temp = j;
                    }
                }
            }

            dp[i][0] = max;
            dp[i][1] = temp;
            dp[i][2] = blocks[i][3];
        }

        int max = 0;
        int temp = 0;
        for(int i = 0; i < N; i++) {
            if(dp[i][0] > max) {
                max = dp[i][0];
                temp = i;
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(dp[temp][2]);
        while(temp != dp[temp][1]) {
            temp = dp[temp][1];
            result.add(dp[temp][2]);
        }

        System.out.println(result.size());
        for(int n : result) {
            System.out.println(n);
        }
    }
}