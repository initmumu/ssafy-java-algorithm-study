import java.io.*;
import java.util.*;

public class Main {
	
	static int[] parents;
	
	// 합집합
	static void union(int x, int y) {
		
		x = find(x);
		y = find(y);
		
		if (x == y) {
			return;
		} else if (x > y ){
			parents[x] = y;
		} else {
			parents[y] = x;
		}	
	}
	
	// 부모 찾기
	static int find(int target) {
		
		if (parents[target] == target) {
			return target;
		} else {
			parents[target] = find(parents[target]);
			return parents[target];
		}
	}

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 학생 수
		int N = Integer.parseInt(st.nextToken());
		// 친구관계 수
		int M = Integer.parseInt(st.nextToken());
		// 가지고 있는 돈
		int k = Integer.parseInt(st.nextToken());
		
		// 각각 학생이 원하는 친구비
		st = new StringTokenizer(br.readLine());
		int[] money = new int[N + 1];
		for (int i = 1; i < N + 1; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}
		
		// 합집합 초기 배열
		parents = new int[N + 1];
		for (int i = 0; i <= N; i++) {
			parents[i] = i;
		}
		
		// 관계로 합집합 연산하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			union(v, w);
		}
		
		// 최소 친구비 계산을 위한 배열 : 최댓값으로 초기화
		int check[] = new int[N + 1];
		Arrays.fill(check, Integer.MAX_VALUE);
		
		for (int i = 1; i < N + 1; i++) {
			int p = find(i);
			check[p] = Math.min(check[p], money[i]);
		}
				
		int ans = 0;
		for (int c : check) {
			if (c != Integer.MAX_VALUE) {
				ans += c;
			}
		}
		
		// 친구비 납부 가능 여부에 따라
		if (ans <= k) {
			System.out.println(ans);
		} else {
			System.out.println("Oh no");
		}
	}
}