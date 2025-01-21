import java.io.*;
import java.util.*;

public class Main {
    static int K, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        long[] lans = new long[K];
        long min = 1;
        long max = 0;

        for(int i = 0; i < K; i++){
            lans[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, lans[i]);
        }

        while(true){
            long mid = (min + max) / 2;
            int count = 0;

            for(int i = 0; i < K; i++){
                count += (int) (lans[i] / mid);
            }

            if(count >= N) {
                min = mid + 1;
            } else {
                max = mid - 1;
            }

            if(min > max) {
                System.out.print(max);
                return;
            }
        }
    }
}
