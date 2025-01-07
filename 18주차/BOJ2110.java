package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =  new StringTokenizer(br.readLine());
		
		// 집 개수
		int N = Integer.parseInt(st.nextToken());
		// 공유기 개수
		int C = Integer.parseInt(st.nextToken());
		
		int[] homes = new int[N];
		for (int i = 0; i < N; i++) {
			homes[i] = Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(homes);
		// 1 2 4 8 9
		
		int start = 1;
		int end = homes[N - 1] - homes[0];
		int ans = 0;
		
		while (start <= end) {
			int mid = (start + end) / 2;
			int cnt = 1;
			int cur = homes[0];
			
			for (int home: homes) {
				if (home - cur >= mid) {
					cnt++;
					cur = home;
				}
			}
		
			// 공유기 설치 더 가능하면
			if (cnt < C) {	
				end = mid - 1;
			// 공유기 설치 덜 해야하면
			} else {
				ans = mid;
				start = mid + 1;
			}
		}
		
		System.out.println(ans);
	}
}