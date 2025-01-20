package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 이미 가진 랜선 개수
		int K = Integer.parseInt(st.nextToken());
		// 필요한 랜선 개수
		int N = Integer.parseInt(st.nextToken());
		
		int[] lens = new int[K];
		for (int i = 0; i < K; i++) {
			lens[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(lens);
		
		long start = 1;
		long end = lens[K - 1];
		long ans = 0;
		
		while (start <= end) {
			long mid = (start + end) / 2;
			int cnt = 0;
			
			for (int i = 0; i < K; i++) {
				cnt += lens[i] / mid;
			}
			
			if (cnt < N) {
				end = mid - 1;
			} else {
				start = mid + 1;
				ans = mid;
			}
		}
		
		System.out.println(ans);
	}
}