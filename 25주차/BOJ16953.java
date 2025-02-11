package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static long A, B;
	static long ans;
	
	static void bfs(long num) {
		
		Queue<long[]> queue = new ArrayDeque<long[]>();
		queue.add(new long[] {A, 1});
		
		while (!queue.isEmpty()) {
			long[] temp = queue.poll();
			long tempNum = temp[0];
			long tempCnt = temp[1];

			// 바꾸기 성공
			if (tempNum == B) {
				ans = tempCnt;
				return;
			}
			
			// 더 커지면 무조건 실패
			if (tempNum > B) {
				continue;
			}
			
			// 2를 곱한다
			queue.add(new long[] {tempNum * 2, tempCnt + 1});
			// 1을 수의 가장 오른쪽에 추가한다
			queue.add(new long[] {tempNum * 10 + 1, tempCnt + 1});
		}
	}
		
		
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 정수 A를 B로 바꿈
		A = Long.parseLong(st.nextToken());
		B = Long.parseLong(st.nextToken());
		
		ans =-1;
		
		bfs(A);
		
		System.out.println(ans);
	}
}