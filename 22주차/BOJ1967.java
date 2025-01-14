package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static ArrayList<int[]>[] tree;
	static int ans, deepNode;
	
	// 가장 먼 노드 찾는 로직
	static void dfs(int node) {
		
		// bfs 하기 위함
		Queue<int[]> queue = new ArrayDeque<int[]>();
		boolean[] visited = new boolean[N + 1];
		visited[node] = true;
				
		for (int i = 0; i < tree[node].size(); i++) {
			int a = tree[node].get(i)[0];
			int b = tree[node].get(i)[1];
			queue.add(new int[] {a, b});
			visited[a] = true;
		}
		
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int next = temp[0];
			int weight = temp[1];
			
			// 리프인지 여부
			boolean isFinal = true;
			
			for (int i = 0; i < tree[next].size(); i++) {
				int[] temp2 = tree[next].get(i); 
				if (!visited[temp2[0]]) {
					queue.add(new int[] {temp2[0], weight + temp2[1]});
					visited[temp2[0]] = true;
					isFinal = false;
				}
			}
			
			// 리프 도달 시 지름 갱신
			if (isFinal) {
				if (ans < weight) {
					ans = weight;
					deepNode = next;
				}
			}
		}
		
		return;
	}

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 노드 개수
		N = Integer.parseInt(br.readLine());
		
		// 인접 리스트 (양방향)
		tree = new ArrayList[N + 1];
		
		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<int[]>();
		}
		
		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			tree[a].add(new int[] {b, c});
			tree[b].add(new int[] {a, c});
		}
		
		// 지름, 가장 깊은 노드
		ans = 0;
		deepNode = 0;
			
		// 루트로부터 가장 먼 노드 찾기
		dfs(1);
		// 가장 깊은 노트로부터 가장 먼 노드 찾기
		dfs(deepNode);
		
		System.out.println(ans);
	}
}