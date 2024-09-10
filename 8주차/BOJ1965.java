import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] nums = new int[N];

        for(int i = 0; i < N; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] DP = new int[N];
        DP[0] = 1;
        int temp;
        int result = 0;
        for(int i = 1; i < N; i++){
            temp = 1;
            for(int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    temp = Math.max(temp, DP[j] + 1);
                }
            }
            DP[i] = temp;
            result = Math.max(result, temp);
        }
        System.out.print(result);
    }

}
