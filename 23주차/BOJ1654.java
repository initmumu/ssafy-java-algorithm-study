import java.io.*;
import java.util.*;


public class BOJ1654 {

	public static void main(String[] args) throws IOException {
		int K = readInt(), N = readInt();
		int[] arr = new int[K];
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < K; i++) {
			int input = readInt();
			max = Math.max(max, input);
			arr[i] = input;
		}
		
		System.out.println(upperBound(arr, 1L, (long) max, N));
	}
	
	public static long upperBound(int[] arr, long start, long end, int t) {
		long result = 0;
		while (start <= end) {
			long mid = (start + end) / 2;
			
			int cnt = 0;
			for (int lan: arr) {
				int div = (int) (lan / mid);
				cnt += div;
			}
			
			if (cnt < t) {
				end = mid - 1;
			} else if (cnt >= t) {
				result = mid;
				start = mid + 1;
			}
		}
		return result;
	}
	
	public static int readInt() throws IOException {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t << 3) + (t << 1) + (c & 15);
		if (c == 13) System.in.read();
		return t;
	}

}