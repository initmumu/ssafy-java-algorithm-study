import java.io.*;
import java.util.*;

public class BOJ_2110 {

    static int N, C;
    static int[] houses;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        houses = new int[N];

        for (int i = 0; i < N; i++) {
            houses[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(houses);

        int start = 1;
        int end = houses[N - 1] - houses[0];
        int result = 1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (isPossible(mid)) {
                result = mid;
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }

        System.out.println(result);
    }

    static boolean isPossible(int dist) {
        int count = 1;
        int curRouter = houses[0];

        int len = houses.length;
        for (int i = 1; i < len; i++) {
            if (houses[i] - curRouter >= dist) {
                curRouter = houses[i];
                count++;
            }
        }

        return count >= C;
    }
}
