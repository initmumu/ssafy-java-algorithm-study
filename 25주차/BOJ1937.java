package algorithm;

import java.io.*;
import java.util.*;

public class Main {
	
	static int n, ans;
	static int[][] forest;
	static int[][] cnts;
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {1, -1, 0, 0};
	
	// 범위 안인지
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < n && y >= 0 && y < n) {
			return true;
		}
		return false;
	}
	
	static int dfs(int x, int y) {
	    // 이미 계산된 경우 패스
	    if (cnts[x][y] != 0) {
	        return cnts[x][y];
	    }
	    
	    // 기본 이동 횟수 1
	    cnts[x][y] = 1;
	    
	    for (int d = 0; d < 4; d++) {
	        int nx = x + dx[d];
	        int ny = y + dy[d];
	        if (isIn(nx, ny) && forest[nx][ny] > forest[x][y]) {
	        	cnts[x][y] = Math.max(cnts[x][y], dfs(nx, ny) + 1);
	        }
	    }
	    
	    return cnts[x][y];
	}

	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 대나무 숲의 크기
		n = Integer.parseInt(br.readLine());
		
		// 대나무 숲
		forest = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				forest[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		cnts = new int[n][n];
		
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int d = 0; d < 4; d++) {
					int nx = i + dx[d];
					int ny = j + dy[d];
					if (isIn(nx, ny) && forest[nx][ny] < forest[i][j]) {
						continue;
					}
				}
				ans = Integer.max(ans, dfs(i, j));
			}
		}
		
		System.out.println(ans);
	}
}