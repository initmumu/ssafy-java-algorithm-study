import java.io.*;
import java.util.*;

public class BOJ4386 {
	public static int N;
	public static double totalDist;
	
	public static int[] parents;
	
	public static int find(int n) {
		if (parents[n] == n) {
			return n;
		}
		
		return find(parents[n]);
	}
	
	public static void union(int n1, int n2) {
		n1 = find(n1);
		n2 = find(n2);
		
		if (n1 > n2) {
			parents[n1] = n2;
		} else {
			parents[n2] = n1;
		}
	}
	
	public static double getDist(double[] star1, double[] star2) {
		double x = star1[0] - star2[0];
		double y = star1[1] - star2[1];
		return Math.sqrt(x * x + y * y);
	}
	
	public static void kruskal(ArrayList<Edge> edges) {
		int cnt = 1;
		for (int i = 0; i < edges.size(); i++) {
			if (cnt == N) break;
			
			Edge e = edges.get(i);
			if (find(e.end) != find(e.start)) {
				union(e.start, e.end);
				totalDist += e.dist;
				cnt++;

			}
			

		}
	}
	
	public static class Edge {
		int start;
		int end;
		double dist;
		
		Edge(int start, int end, double dist) {
			this.start = start;
			this.end = end;
			this.dist = dist;
			
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		double[][] stars = new double[N][2];
		parents = new int[N];
		
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
		
		ArrayList<Edge> edges = new ArrayList<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			stars[i][0] = Double.parseDouble(st.nextToken());
			stars[i][1] = Double.parseDouble(st.nextToken());
		}
		
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				edges.add(new Edge(i, j, getDist(stars[i], stars[j])));
			}
		}
		
		edges.sort(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return (int) (o1.dist - o2.dist);
			}
			
		});
		kruskal(edges);
		
		System.out.println(Math.round(totalDist * 100) / 100.);
		
	}

}
