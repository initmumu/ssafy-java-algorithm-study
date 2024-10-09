import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int resultA = 0, resultB = 0;
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N - 1; i++) {
            int current = nums[i];

            int start = i + 1;
            int end = N - 1;
            int closest = 0;

            while (start <= end) {
                int mid = (start + end) / 2;

                int sum = current + nums[mid];

                if (Math.abs(sum) < min) {
                    min = Math.abs(sum);
                    resultA = current;
                    resultB = nums[mid];
                }

                if (sum > 0) {
                    end = mid - 1;
                }
                else {
                    start = mid + 1;
                }
            }
        }

        System.out.println(resultA + " " + resultB);
    }
}
