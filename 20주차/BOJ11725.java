package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 노드의 개수
		int N = Integer.parseInt(br.readLine());
		
		ArrayList<Integer>[] tree = new ArrayList[N + 1];
		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<Integer>();
		}
		
		// 입력받아서 트리 구성하기
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			tree[a].add(b);
			tree[b].add(a);
		}
		
		// 부모 노드 저장할 곳
		int[] parents = new int[N + 1];
		
		// 방문 여부
		boolean[] visited = new boolean[N + 1];
		
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(1);
		visited[1] = true;
		
		// 부모 노드 계산해서 저장하기
		while (!queue.isEmpty()) {
			int node = queue.poll();
			
			for (int i = 0; i < tree[node].size(); i++) {
				if (!visited[tree[node].get(i)]) {
					parents[tree[node].get(i)] = node;
					visited[tree[node].get(i)] = true;
					queue.add(tree[node].get(i));
				}
			}
		}
		
		// 정답 출력
		for (int i = 2; i < N + 1; i++) {
			System.out.println(parents[i]);
		}
	}
}