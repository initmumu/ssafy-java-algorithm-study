package algorithm;

import java.io.*;
import java.util.*;

// 별
class Star {
	int num;
	double x;
	double y;
	
	public Star(int num, double x, double y) {
		this.num = num;
		this.x = x;
		this.y = y;
	}	
}

// 별들 간의 간선
class Edge implements Comparable<Edge>{
	int start;
	int end;
	double dist;
	
	public Edge(int start, int end, double dist) {
		this.start = start;
		this.end = end;
		this.dist = dist;
	}

	@Override
	public int compareTo(Edge o) {
		if (this.dist == o.dist) {
			if (this.end == o.end) {
				return Integer.compare(this.start, o.start);
			}
			return Integer.compare(this.end, o.end);
		}
		return Double.compare(this.dist, o.dist);
	}	
}

public class Main {
	
	static int[] parents;
	
	static int find(int a) {
		
		if (parents[a] == a) {
			return a;
		} else {
			parents[a] = find(parents[a]);
			return parents[a];
		}
	}
	
	static boolean union(int a, int b) {
		
		a = find(a);
		b = find(b);
		
		// 이미 같은 집합이면 union 안함
		if (a == b) {
			return false;
		// 다른 집합이면 union 실행
		} else {
			parents[b] = find(a);
			return true;
		}
	}
	

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 별의 개수
		int N = Integer.parseInt(br.readLine());
		// 별들
		Star[] stars = new Star[N];
		
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double x = Double.parseDouble(st.nextToken());
			double y = Double.parseDouble(st.nextToken());
			stars[i] = new Star(i, x, y);
		}
		
		// 간선들
		ArrayList<Edge> edges = new ArrayList<Edge>();
		
		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				double dist = Math.sqrt(Math.pow(stars[i].x - stars[j].x, 2) + Math.pow(stars[i].y - stars[j].y, 2));
				edges.add(new Edge(stars[i].num, stars[j].num, dist));
			}
		}
		
		// 간선을 거리 기준으로 오름차순 정렬
		Collections.sort(edges);
		
		// 부모 배열 초기화
		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		// 크루스칼 알고리즘
		double ans = 0;
		
		for (int i = 0; i < edges.size(); i++) {
			Edge edge = edges.get(i);
			
			// (start, end)가 아직 연결되지 않았다면 MST에 추가
			if (find(edge.start) != find(edge.end)) {
				ans += edge.dist;
				union(edge.start, edge.end);
			}
		}
		
		System.out.printf("%.2f", ans);		
	}
}