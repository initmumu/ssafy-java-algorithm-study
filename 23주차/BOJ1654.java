import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1654 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        long min = 1;
        long max = 0;

        int[] LAN = new int[K];
        for (int i = 0; i < K; i++) {
            LAN[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, LAN[i]);
        }

        while (min <= max) {
            int count = 0;
            long mid = (max + min) / 2;

            for (int i = 0; i < K; i++) {
                count += LAN[i] / mid;
            }

            if (count < N) {
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
        }

        System.out.println(max);
    }
}
