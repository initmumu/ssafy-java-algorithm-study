import java.io.*;
import java.util.*;

public class BOJ2470 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int[] value = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(value);
		
		int v1 = 0, v2 = 0;
		int min = Integer.MAX_VALUE;
		
		int left = 0;
        int right = N - 1;
        while (left < right) {
            int sum = value[left] + value[right];
            if (Math.abs(sum) < min) {
            	min = Math.abs(sum);
                v1 = value[left];
                v2 = value[right];
            }
            
            if (sum < 0) {
                left++;
            } 
            else {
                right--;
            }
        }
		
		System.out.println(v1 + " " + v2);
	}
}
