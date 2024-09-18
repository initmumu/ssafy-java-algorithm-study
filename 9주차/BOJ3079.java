import java.io.*;
import java.util.*;

public class BOJ3079 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long M = Integer.parseInt(st.nextToken());

        long[] T = new long[N];
        long max = 0;
        for (int i = 0; i < N; i++) {
            T[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, T[i]);
        }

        // 이진 탐색
        long low = 1;
        long high = max * M;
        long result = high;

        while (low <= high) {
            long mid = (low + high) / 2;

            if (canProcess(T, mid, M)) {
                result = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(result);
    }

    // time 시간 내에 M명 이상 처리할 수 있는지 확인
    static boolean canProcess(long[] T, long time, long M) {
        long total = 0;
        for (long t : T) {
            total += time / t;
            if (total >= M) {
                return true;
            }
        }
        return false;
    }
}
