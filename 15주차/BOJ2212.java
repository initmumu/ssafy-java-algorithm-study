import java.io.*;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] arr2 = new int[N - 1];
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        for(int i = 0; i < N - 1; i++){
            arr2[i] = Math.abs(arr[i] - arr[i + 1]);
        }

        Arrays.sort(arr2);
        int result = 0;
        for(int i = 0; i < N - M; i++){
            result += arr2[i];
        }
        System.out.println(result);
    }

}
