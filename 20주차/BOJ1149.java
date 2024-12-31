import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ1149 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int min_R = 0, min_G = 0, min_B = 0;

        for (int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            int R = Integer.parseInt(input[0]);
            int G = Integer.parseInt(input[1]);
            int B = Integer.parseInt(input[2]);

            if (i == 0) {
                min_R = R;
                min_G = G;
                min_B = B;
                continue;
            }
            int temp_R = Math.min(min_G, min_B) + R;
            int temp_G = Math.min(min_R, min_B) + G;
            int temp_B = Math.min(min_R, min_G) + B;

            min_R = temp_R;
            min_G = temp_G;
            min_B = temp_B;
        }

        System.out.println(Math.min(Math.min(min_R, min_G), min_B));
    }
}
