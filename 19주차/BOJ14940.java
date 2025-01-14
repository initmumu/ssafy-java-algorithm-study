import java.io.*;
import java.util.*;

public class BOJ14940 {
	static class VisitNode {
		int x, y, dist;
		VisitNode(int x, int y, int dist) {
			this.x = x;
			this.y = y;
			this.dist = dist;
		}
	}
	
	static int N, M;
	
	public static void bfs(int[][] grid, int[][] ans, VisitNode vn) {
		int[] dx = {-1,1,0,0};
		int[] dy = {0,0,-1,1};
		
		boolean[][] visited = new boolean[N][M];
		ArrayDeque<VisitNode> q = new ArrayDeque<>();
		q.add(vn);
		visited[vn.x][vn.y] = true;
		
		while(!q.isEmpty()) {
			VisitNode node = q.poll();
			ans[node.x][node.y] = node.dist;
	
			for (int d = 0; d < 4; d++) {
				int nx = node.x + dx[d];
				int ny = node.y + dy[d];
				
				if (-1 < nx && nx < N && -1 < ny && ny < M && !visited[nx][ny]) {
					if (grid[nx][ny] == 0) {
						visited[nx][ny] = true;
						ans[nx][ny] = 0;
					} else if (grid[nx][ny] == 1) {
						visited[nx][ny] = true;
						q.add(new VisitNode(nx, ny, node.dist+1));
					}
				}
			}
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j]) ans[i][j] = -1;
				if (grid[i][j] == 0) ans[i][j] = 0;
			}
		}
		
		printGrid(ans);
	}
	
	public static void printGrid(int[][] ans) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(ans[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
	

	public static void main(String[] args) throws IOException {
		N = read(); M = read();
		int[][] grid = new int[N][M];
		int[][] ans = new int[N][M];
		
		VisitNode start = null;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				int pos = read();
				
				if(pos == 2) {
					start = new VisitNode(i, j, 0);
				}
				grid[i][j] = pos;
			}
		}
		
		bfs(grid, ans, start);
	}
	
	public static int read() throws IOException {
		int c, t = 0; 
		while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
		return t;
	}

}
