import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            String[] phoneNumbers = new String[N];

            for (int i = 0; i < N; i++) {
                phoneNumbers[i] = br.readLine();
            }

            Arrays.sort(phoneNumbers);

            String result = "YES";
            for (int i = 0; i < N - 1; i++) {
                String current = phoneNumbers[i];
                String next = phoneNumbers[i + 1];

                if (current.length() > next.length()) {
                    continue;
                }
                if (next.substring(0, current.length()).equals(current)) {
                    result = "NO";
                    break;
                }
            }

            System.out.println(result);
        }
    }
}
