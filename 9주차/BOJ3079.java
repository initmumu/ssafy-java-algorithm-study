import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static long[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new long[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        long min = 0, max = arr[N - 1] * M;

        long result = max;
        while (min < max) {
            long mid = (min + max) / 2;
            if (cal(mid)) {
                result = mid;
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        System.out.print(result);
    }

    public static boolean cal(long time) {
        long count = 0;
        for (long i : arr) {
            count += time / i;
            if (count >= M) {
                return true;
            }
        }
        return false;
    }
}
