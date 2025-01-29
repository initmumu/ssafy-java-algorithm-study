package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 표의 크기
		int N = Integer.parseInt(st.nextToken());
		// 합 구해야 하는 횟수
		int M = Integer.parseInt(st.nextToken());
		
		// 배열
		int[][] arr =  new int[N + 1][N + 1];
		
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j < N + 1; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 가로 누적합
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < N; j++) {
				arr[i][j + 1] += arr[i][j];
			}
		}
		
		// 새로 누적합
		for (int i = 1; i < N + 1; i++) {
			for (int j = 0; j < N; j++) {
				arr[j + 1][i] += arr[j][i];
			}
		}
		
		// 합 구하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			int ans = arr[x2][y2] - arr[x1 - 1][y2] - arr[x2][y1 - 1] + arr[x1 - 1][y1 - 1];
			System.out.println(ans);
		}
		
	}
}