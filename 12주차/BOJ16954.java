import java.io.*;
import java.util.*;

public class Main {
	
	static char[][] arr;
	static int ux, uy, tx, ty;
	static Queue<int[]> queue;
	static boolean[][] visited;
	static int[] dx = {0, -1, 1, 0, 0, -1, -1, 1, 1};
	static int[] dy = {0, 0, 0, -1, 1, -1, 1, -1, 1};
	static boolean can;
	
	// 체스판 범위 내 여부 확인하는 메서드
	static boolean isIn(int x, int y) {
		if (x < 8 && x >= 0 && y < 8 && y >= 0) {
			return true;
		}
		return false;
	}
	
	// 벽 아래로 이동하는 메서드
	static void moveWall() {
		
		for (int i = 7; i >= 0; i--) {
			for (int j = 0; j < 8; j++) {
				// 해당 위치가 벽이면
				if (arr[i][j] == '#') {
					int x = i + 1;
					int y = j;
					// 범위 내에 있으면 이동하기
					if (isIn(x, y)) {
						arr[i][j] = '.';
						arr[x][y] = '#';
					// 범위 내에 없으면 벽 사라짐
					} else {
						arr[i][j] = '.';
					}
				}	
			}
		}		
	}
	
	
	static void bfs() {
		
		while (true) {
			// 큐의 현재 길이
			int len = queue.size();
			if (len == 0) {
				return;
			}
			
			visited = new boolean[8][8];
			
			// 큐 사이즈만큼 반복하기
			for (int i = 0; i < len; i++) {
				int[] t = queue.poll();
				for (int d = 0; d < 9; d++) {
					int nx = t[0] + dx[d];
					int ny = t[1] + dy[d];
					
					if (arr[t[0]][t[1]] != '#' && isIn(nx, ny) && visited[nx][ny] == false && arr[nx][ny] != '#') {
						int[] te = {nx, ny};
						queue.add(te);
						visited[nx][ny] = true;
						
						// 만일 목적지라면 리턴
						if (nx == tx && ny == ty) {
							can = true;
							return;
						}
					}
				}
			}
			moveWall();
		}	
	}
	
	
    public static void main(String[] args) throws Exception {
    
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	
    	// 체스판
    	arr = new char[8][8];
    	
    	for (int i = 0; i < 8; i++) {
    		String temp = br.readLine();
    		for (int j = 0; j < 8; j++) {
    			arr[i][j] = temp.charAt(j);
    		}
    	}

    	// 욱제의 현재 위치
    	ux = 7;
    	uy = 0;
    	
    	// 목적지
    	tx = 0;
    	ty = 7;
    	
    	visited = new boolean[8][8];
    	can = false;
    	
    	// bfs에 사용할 큐
		queue = new ArrayDeque<>();
		int[] t = {ux, uy};
		queue.add(t);
		visited[ux][uy] = true;
		
		bfs();
		
		if (can) {
			System.out.println(1);
		} else {
			System.out.println(0);
		} 	
    }
}