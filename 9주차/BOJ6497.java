import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        int N = read(), M = read();
        long[] time = new long[N];
        long minV = Long.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int t = read();
            time[i] = t;
            minV = Math.min(minV, t);
        }

        long high = M * minV, low = 1, ans = Long.MAX_VALUE;

        while (low <= high) {
            long people = 0;
            long mid = (low + high) / 2;
            for (int i = 0; i < N; i++) people += mid / time[i];

            if (people >= M) {
                ans = Math.min(ans, mid);
                high = mid - 1;
            } else low = mid + 1;
        }

        System.out.println(ans);
    }

    public static int read() throws IOException {
        int c, t= 0;
        while((c= System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
        return t;
    }
}
