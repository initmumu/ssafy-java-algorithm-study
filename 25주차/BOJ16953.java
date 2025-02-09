import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16953 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        int count = 1;

        while (B > A) {
            if (B % 2 == 0) {
                B /= 2;
            }
            else if (B % 10 == 1) {
                B /= 10;
            }
            else {
                System.out.println(-1);
                return;
            }

            count++;
        }

        System.out.println(A == B ? count : -1);
    }
}
