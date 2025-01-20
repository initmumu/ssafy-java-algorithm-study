package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static ArrayList<Integer> parentsA;
	static ArrayList<Integer> parentsB;
	
	static int findP() {
		
		// A의 조상을 집합으로 저장
		Set<Integer> set = new HashSet<>(parentsA);
		for (int par : parentsB) {
			if (set.contains(par)) {
				return par;
			}
		}
		
		return 0;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 테스트케이스 개수
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			// 노드 개수
			int N = Integer.parseInt(br.readLine());
			
			// 인접 리스트
			ArrayList<Integer>[] tree = new ArrayList[N + 1];
			for (int i = 0; i < N + 1; i++) {
				tree[i] = new ArrayList<Integer>();
			}
			
			for (int i = 0; i < N - 1; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				tree[b].add(a);
			}
			
			st = new StringTokenizer(br.readLine());
			// 두 노드
			int targetA = Integer.parseInt(st.nextToken());
			int targetB = Integer.parseInt(st.nextToken());
			
			// A 부모 모음
			parentsA = new ArrayList<>();
			// B 부모 모음
			parentsB = new ArrayList<>();
			
			parentsA.add(targetA);
			parentsB.add(targetB);
			
			// A 부모 저장
			while (true) {
				if (tree[targetA].size() == 0) {
					break;
				}
				int tempA = tree[targetA].get(0);
				parentsA.add(tempA);
				targetA = tempA;
			}
			
			// B 부모 저장
			while (true) {
				if (tree[targetB].size() == 0) {
					break;
				}
				int tempB = tree[targetB].get(0);
				parentsB.add(tempB);
				targetB = tempB;
			}
			
			System.out.println(findP());
		}
	}
}