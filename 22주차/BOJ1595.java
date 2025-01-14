package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static HashMap<Integer, List<int[]>> roads;
	static int maxNode, ans;
	
	static void findLong(int start) {
		
		// 탐색 위한 방문 해시맵
		HashMap<Integer, Boolean> visited = new HashMap<Integer, Boolean>();
		visited.put(start, true);
		
		// 처음 값 넣기
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {start, 0});
		
		while (!queue.isEmpty()) {
			int[] tempCity = queue.poll();
			int node = tempCity[0];
			int weight = tempCity[1];
			
			// 최대 거리 업데이트
			if (weight > ans) {
				ans = weight;
				maxNode = node;
			}
			
			// 인접 노드 탐색하기
			for (int[] nodes : roads.getOrDefault(node, new ArrayList<>())) {
				int nextNode = nodes[0];
				int nextWeight = nodes[1];
				if (!visited.containsKey(nextNode)) {
					visited.put(nextNode, true);
					queue.add(new int[] {nextNode, weight + nextWeight});
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 도로 저장 위한 해시맵
		roads = new HashMap<>();
		
		// 탐색 시작할 곳
		int start = 0;
		
		String input;
		while ((input = br.readLine()) != null && !input.isEmpty()) {
			StringTokenizer st = new StringTokenizer(input);
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			// 탐색 시작할 임의의 노드
			start = x;
			
			// x -> y 방향
			roads.putIfAbsent(x, new ArrayList<>());
			roads.get(x).add(new int[] {y, w});
			// y -> x 방향
			roads.putIfAbsent(y, new ArrayList<>());
			roads.get(y).add(new int[] {x, w});
		}
		
		// 처음 최댓값인 노드
		maxNode = 0;
		// 정답
		ans = 0;
		
		// 임의의 노드로부터 가장 먼 노드 구하기
		findLong(start);
		// 그로부터 가장 먼 노드 구하기 마치 트리의 지름 찾기
		findLong(maxNode);
		
		System.out.println(ans);
	}
}