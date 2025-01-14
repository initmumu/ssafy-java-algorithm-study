import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6236 {

    static int N, M;
    static int[] money;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int min = 0;
        int max = 0;

        money = new int[N];
        for (int i = 0; i < N; i++) {
            money[i] = Integer.parseInt(br.readLine());
            min = Math.max(min, money[i]);
            max += money[i];
        }

        int result = max;
        while (min <= max) {
            int mid = (min + max) / 2;

            if (isPossible(mid)) {
                result = mid;
                max = mid - 1;
            }
            else {
                min = mid + 1;
            }
        }

        System.out.println(result);
    }

    static boolean isPossible(int mid) {
        int count = 1;
        int current = mid;

        for (int m: money) {
            // 통장에서 뺀 돈으로 하루를 보낼 수 없음
            // 남은 금액은 통장에 집어넣고 다시 K원을 인출
            if (current < m) {
                // 인출 횟수가 M보다 커지면 false
                if (count > M) {
                    return false;
                }

                count++;
                current = mid;
            }
            // 통장에서 뺀 돈으로 하루를 보낼 수 있음
            current -= m;
        }
        return count <= M;
    }
}
