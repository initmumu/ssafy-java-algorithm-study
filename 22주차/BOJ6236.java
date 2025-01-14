package algorithm;

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 총 일수
		int N = Integer.parseInt(st.nextToken());
		// 인출 횟수
		int M = Integer.parseInt(st.nextToken());
		
		// 각 날에 이용할 금액
		int[] amounts = new int[N];
		int maxAmount = 0;
		
		// 이분탐색 위한 값 (가능한 가장 작은 값, 가능한 가장 큰 값)
		int start = Integer.MAX_VALUE;
		int end = 0;
		
		// 정답
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			amounts[i] = Integer.parseInt(br.readLine());
			start = Integer.min(start, amounts[i]);
			end += amounts[i];
			maxAmount = Integer.max(maxAmount, amounts[i]);
		}
		
		while (start <= end) {
			// 중간값으로 초기화
			int mid = (start + end) / 2;
			int money = mid;
			// 인출 횟수 초깃값
			int cnt = 1;
			
			// 각 날에 이용할 금액 순회하며 인출 횟수 세기
			for (int a : amounts) {
				if (money - a < 0) {
					money = mid;
					cnt++;
				}
				money -= a;
			}
			
			// 출금 횟수가 M 초과거나, mid 값이 하루 사용하는 최대 금액보다 작다면
			// start 갱신하여 인출 금액 늘리기
			if (cnt > M || mid < maxAmount) {
				start = mid + 1;
			// 그렇지 않다면 end 갱신하여 인출 금액 줄이기
			} else {
				end = mid - 1;
				ans = mid;
			}
		}
		
		System.out.println(ans);
	}
}