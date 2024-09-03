import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1753 {
	
	public static ArrayList<ArrayList<Node>> graph;
	public static int [] dist;
	public static final int INF = Integer.MAX_VALUE;
	
	public static class Node implements Comparable<Node> {
		int to;
		int weight;
		
		public Node(int to, int weight) {
			super();
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight-o.weight;
		}
	}
	
	public static void dijkstra_pq(int start, int end) {
		
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		graph = new ArrayList<>();
		for (int i = 0; i<=V; i++) {
			graph.add(new ArrayList<Node>());
		}

		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph.get(from).add(new Node(to, weight));
		}
		
		dist = new int [V+1];
		Arrays.fill(dist, INF);
		
		dist[start] = 0;
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		pq.offer(new Node(start, 0));
		
		while (!pq.isEmpty()) {
			Node node = pq.poll();
			int to = node.to;
			int weight = node.weight;
			
			if (weight > dist[to]) continue;
			
			for (Node next : graph.get(to)) {
				int nextTo = next.to;
				int nextWeight = next.weight;
				int newDist = dist[to] + nextWeight;
				
				if (newDist < dist[nextTo]) {
					dist[nextTo] = newDist;
					pq.add(new Node(nextTo, newDist));
				}
				
			}
		}
		
		for (int i = 1; i <= V; i++) {
			if (dist[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(dist[i]);
			}
		}
		
	}


}
