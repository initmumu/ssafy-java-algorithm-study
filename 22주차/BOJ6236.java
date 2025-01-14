import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ6236 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] e = new int[n];
        int maxE = 0;
        int sumE = 0;

        for (int i = 0; i < n; i++) {
            e[i] = Integer.parseInt(br.readLine());
            maxE = Math.max(maxE, e[i]);
            sumE += e[i];
        }

        int result = findMin(n, m, e, maxE, sumE);
        System.out.println(result);
    }

    private static int findMin(int n, int m, int[] e, int maxE, int sumE) {
        int left = maxE;
        int right = sumE;
        int result = sumE;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (isValid(mid, e, m)) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return result;
    }

    private static boolean isValid(int k, int[] e, int m) {
        int count = 1;
        int bal = k;

        for (int x : e) {
            if (bal < x) {
                count++;
                bal = k;
            }
            bal -= x;

            if (count > m) {
                return false;
            }
        }

        return true;
    }
}
