import java.io.*;
import java.util.*;

// 노드 클래스
class Node implements Comparable<Node>{
	int end;
	int dist;
	
	public Node(int end, int dist) {
		super();
		this.end = end;
		this.dist = dist;
	}

	@Override
	public int compareTo(Node o) {
		return Integer.compare(this.dist, o.dist);
	}
}

public class Main {

	static int N;
	static List<Node>[] nodes;
	static int[] nums;
	static boolean[] dfsVisited;
	static int[][] dists;
	static int minDist;
	static int[] minComb;
	
	// 치킨집 세울 위치 조합 탐색
	static void dfs(int depth, int start) {
		
		// 2개가 정해지면, 그 2개 기준 거리 구해서 정답 갱신
		if (depth == 2) {
			findMin(nums[0], nums[1]);
			return;
		}
		
		for (int i = start; i < N + 1; i++) {
			if (!dfsVisited[i]) {
				dfsVisited[i] = true;
				nums[depth] = i;
				dfs(depth + 1, i + 1);
				dfsVisited[i] = false;
			}
		}
	}
	
	// 치킨집을 a, b 위치에 세울 경우 거리 계산하여 정답 갱신
	static void findMin(int a, int b) {
		
		// 해당 위치 기준 거리
		int tempDist = 0;
		for (int i = 1; i < N + 1; i++) {
			tempDist += Integer.min(dists[a][i], dists[b][i]);
		}
		
		// 거리가 가깝다면 갱신
		if (tempDist < minDist) {
			minDist = tempDist;
			minComb[0] = a;
			minComb[1] = b;
		} else if (tempDist == minDist) {
			// 건물 조합이 다양하게 가능하면, 작은 번호가 더 작은 것을
			if (a < minComb[0]) {
				minDist = tempDist;
				minComb[0] = a;
				minComb[1] = b;
			// 작은 번호가 같다면 큰 번호가 더 작은 것을
			} else if (a == minComb[0] && b < minComb[1]) {
				minDist = tempDist;
				minComb[0] = a;
				minComb[1] = b;
			}
		}
	}
	
	// 다익스트라로 각 건물별 최소 거리 dists[][] 배열 채우기
	static int dij(int start, int end) {
		
		int[] dist = new int[N + 1];
		for (int i = 0; i < N + 1; i++) {
			dist[i] = Integer.MAX_VALUE;
		}
		
		dist[start] = 0;
		
		boolean[] visited = new boolean[N + 1];
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.add(new Node(start, 0));
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			
			if (!visited[node.end]) {
				visited[node.end] = true;
			}
			
			for (int i = 0; i < nodes[node.end].size(); i++) {
				Node no = nodes[node.end].get(i);
				
				if (!visited[no.end] && node.dist + no.dist < dist[no.end]) {
					dist[no.end] = node.dist + no.dist;
					queue.add(new Node(no.end, dist[no.end]));
				}
			}
		}
		return dist[end];
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 건물 개수
		N = Integer.parseInt(st.nextToken());
		// 도로 개수
		int M = Integer.parseInt(st.nextToken());
		
		nodes = new ArrayList[N + 1];
		for (int i = 1; i < N + 1; i++) {
			nodes[i] = new ArrayList<Node>();
		}
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			nodes[a].add(new Node(b, 1));
			nodes[b].add(new Node(a, 1));
		}
		
		// 건물 사이의 모든 거리 구하기
		dists = new int[N + 1][N + 1];
		for (int i = 1; i < N + 1; i++) {
			for (int j = i + 1; j < N + 1; j++) {
				dists[i][j] = dij(i, j) * 2;
				dists[j][i] = dij(j, i) * 2;
			}
		}
		
		// dfs를 위한 것들
		nums = new int[2];
		dfsVisited = new boolean[N + 1];
		
		// findMin을 위한 것들
		minDist = Integer.MAX_VALUE;
		minComb = new int[2];
		
		dfs(0, 1);
		
		// 정답 만들기
		StringBuilder sb = new StringBuilder();
		sb.append(minComb[0]).append(" ").append(minComb[1]).append(" ").append(minDist);
		
		System.out.println(sb);
		
	}
}