package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[][] arr;
	
	static int[][] daq(int[][] tempArr, long sqr) {
		
		if (sqr == 1) {
			return tempArr;
		}
		
		// 분할정복
		int[][] divid = daq(tempArr, sqr / 2);
		
		// 제곱
		divid = gop(divid, divid);
		
		// 홀수면 한번 더 곱해야해
		if (sqr % 2 != 0) {
			divid = gop(divid, arr);
		}
		return divid;
	}
	
	// 행렬 곱셈 연산
	static int[][] gop(int[][] arr1, int[][] arr2) {
		int[][] gopArr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					gopArr[i][j] += arr1[i][k] * arr2[k][j];
					gopArr[i][j] %= 1000;
				}
			}
		}
		return gopArr;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 행렬의 크기
		N = Integer.parseInt(st.nextToken());
		// 제곱수
		long B = Long.parseLong(st.nextToken());
		
		// 행렬
		arr = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) % 1000;
			}
		}
		
		// 제곱 결과
		int[][] ans = daq(arr, B);
		
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(ans[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}