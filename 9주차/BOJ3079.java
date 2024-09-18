import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ3079 {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] times = new int[N];
        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
        }

        long left = 1;
        long right = (long) times[0] * M;
        for (int i = 1; i < N; i++) {
            right = Math.max(right, (long) times[i] * M);
        }

        long result = right;

        while (left <= right) {
            long mid = (left + right) / 2;
            long totalPeople = 0;

            for (int i = 0; i < N; i++) {
                totalPeople += mid / times[i];
                if (totalPeople >= M) break;
            }

            if (totalPeople >= M) {
                result = mid; 
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(result);
    }
}
