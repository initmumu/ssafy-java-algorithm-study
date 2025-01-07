package algorithm;

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 트리 노드 개수
		int N = Integer.parseInt(br.readLine());
		
		// 트리 인접 리스트
		ArrayList<Integer>[] tree = new ArrayList[N];
		for (int i = 0; i < N; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		// 루드
		int root = -1;
		
		// 정보 담기
		// 자식 정보가 담김
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			int input = Integer.parseInt(st.nextToken());
			if (input != -1) {
				tree[input].add(i);
			} else {
				root = i;
			}
		}
		
		int target = Integer.parseInt(br.readLine());
		
		// 자르기
		Queue<Integer> queue = new ArrayDeque<Integer>();
		queue.add(target);
		boolean[] gone = new boolean[N];
		// 사라졌는지 여부
		gone[target] = true;
		
		while (!queue.isEmpty()) {
			int node = queue.poll();
			for (int i = 0; i < tree[node].size(); i++) {
				queue.add(tree[node].get(i));
				gone[tree[node].get(i)] = true;
			}
		}
		
		// 리프 노드 개수
		int ans = 0;
		
		for (int i = 0; i < N; i++) {
			// 짤리지 않았을 경우만 검사 (루트는 검사 제외)
			if (!gone[i] && i != root) {
				boolean isLeaf = true;
				// 자식이 없거나 자식이 다 짤렸다면 리프
				for (int j = 0; j < tree[i].size(); j++) {
					if (!gone[tree[i].get(j)]) {
						isLeaf = false;
					}
				}
				// 리프면
				if (isLeaf) {
					ans++;
				}
			} 
		}
				
		// 루트 노드 검사
		boolean isLeaf = true;
		if (!gone[root]) {
			for (int i = 0; i < tree[root].size(); i++) {
				if (!gone[tree[root].get(i)]) {
					isLeaf = false;
				}
			}
		} else {
			isLeaf = false;
		}
		
		if (isLeaf) {
			ans++;
		}

		System.out.println(ans);
	}
}