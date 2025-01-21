import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1654 {

    static int k, n;
    static long[] lengths;
    static long maxLength = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        lengths = new long[k];

        for (int i = 0; i < k; i++) {
            lengths[i] = Long.parseLong(br.readLine());
            maxLength = Math.max(maxLength, lengths[i]);
        }

        long result = findMaxLength();
        System.out.println(result);
    }

    static long findMaxLength() {
        long left = 1;
        long right = maxLength;
        long result = 0;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (canMake(mid)) {
                result = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return result;
    }

    static boolean canMake(long length) {
        long count = 0;

        for (long cable : lengths) {
            count += cable / length;
        }

        return count >= n;
    }
}
