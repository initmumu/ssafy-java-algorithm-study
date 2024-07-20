import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static boolean bs(int[] arr, int x, int s, int e) {
        if(s > e) return false;

        int mid = (s + e) / 2;
        if(arr[mid] == x) return true;
        else if (arr[mid] < x) return bs(arr, x, mid+1, e);
        else return bs(arr, x, s, mid-1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        int i = 0;
        for (String s : br.readLine().split(" "))
            arr[i++] = Integer.parseInt(s);

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int cnt = 0;

        int sum = 0;
        for(i = 0; i < n; i++) {
            if (bs(arr, x - arr[i], i+1, n-1)) cnt++;
        }

        System.out.println(cnt);
    }
}
