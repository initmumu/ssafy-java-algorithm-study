import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class SWEA1288 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            HashSet<Character> set = new HashSet<>();

            int cnt = 0;
            while (set.size() != 10) {
                String num = String.valueOf(++cnt * N);
                for (int i = 0; i < num.length(); i++) {
                    set.add(num.charAt(i));
                }
            }

            System.out.println("#" + t + " " + cnt * N);
        }
    }
}
