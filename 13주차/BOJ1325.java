package algorithm;

import java.io.*;
import java.util.*;

public class Problem {
	
	static int N, M;
	static ArrayList <Integer>[] arr;
	static boolean[] visited;
	static int[] cnts;
	
	static void bfs(int a) {
		
		Queue<Integer> queue = new ArrayDeque<>();
		
		queue.add(a);
		visited[a] = true;
		
		while (!queue.isEmpty()) {
			int n = queue.poll();
			for (int i : arr[n]) {
				if (!visited[i]) {
					cnts[i]++;
					visited[i] = true;
					queue.add(i);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 컴퓨터 개수
		N = Integer.parseInt(st.nextToken());
		// 신뢰하는 관계 계수
		M = Integer.parseInt(st.nextToken());
		
		// 인접리스트 초기화
		arr = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			arr[A].add(B);
		}
		
		// 해킹 가능한 컴퓨터 개수 저장할 배열
		cnts = new int[N + 1];
		
		// 각 컴퓨터마다 bfs 돌리기
		for (int i = 1; i < N + 1; i++) {
			visited = new boolean[N + 1];
			bfs(i);
		}
		
		// 최댓값 구하기
		int max = Integer.MIN_VALUE;
		
		for (int i = 1; i < N + 1; i++) {
			if (max < cnts[i]) {
				max = cnts[i];
			}
		}
		
		// 최댓값에 맞는 컴퓨터 번호 오름차순으로 출력
		StringBuilder sb = new StringBuilder();
		for (int i = 1; i < N + 1; i++) {
			if (cnts[i] == max) {
				sb.append(i).append(" ");
			}
		}
		
		System.out.println(sb);
	}
}