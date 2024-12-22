import java.io.*;
import java.util.*;

public class Main {
    static int N, C;
    static int[] arr;
    static int max;

    public static boolean canDo(int distance) {
        int prev = arr[0];
        int count = 1;
        for(int i = 0; i < N; i++){
            if(arr[i] - prev >= distance) {
                prev = arr[i];
                count++;
            }
        }
        return count >= C;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        max = 1;
        arr = new int[N];

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int low = 1;
        int high = arr[N - 1] - arr[0];

        while(low <= high) {
            int mid = (high + low) / 2;

            if(canDo(mid)) {
                max = mid;
                low = mid + 1;
            } else{
                high = mid - 1;
            }
        }
        System.out.println(max);
    }

}
