package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	// 방향 : 북 동 남 서
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static int[][] grid;
	static int N, M, r, c, d;
	
	// 범위 내에 있나 여부
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M && grid[x][y] != 1) {
			return true;
		}
		return false;
	}
	
	// 청소되지 않은 빈 칸 있는지 여부
	static boolean canMove() {
		for (int d = 0; d < 4; d++) {
			int nx = r + dx[d];
			int ny = c + dy[d];
			if (isIn(nx, ny) && grid[nx][ny] == 0) {
				return true;
			}
		}
		return false;
	}
	
	// 반시계 방향으로 90도 회전
	static void turn() {
		for (int i = 0; i < 4; i++) {
			d = (d + 3) % 4;
			int nr = r + dx[d];
			int nc = c + dy[d];
			if (grid[nr][nc] == 0) {
				r = nr;
				c = nc;
				return;
			}
		}
	}
	
	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 방의 크기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		// 로봇청소기 좌표
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		// 바라보는 방향 
		d = Integer.parseInt(st.nextToken());
		
		// 장소
		grid = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//  청소하는 칸 개수
		int cnt = 0;
		
		while (true) {
			
			if (grid[r][c] == 0) {
				grid[r][c] = 2;
				cnt++;
			}
			
			// 청소되지 않은 빈 칸이 있는 경우
			if (canMove()) {
				turn();
			// 청소되지 않은 빈 칸이 없는 경우
			} else {
				int nd = (d + 2) % 4;
				int nr = r + dx[nd];
				int nc = c + dy[nd];
				
				// 후진 가능하면 하기
				if (isIn(nr, nc) && grid[nr][nc] != 1) {
					r = nr;
					c = nc;
				// 후진 불가면 작동 멈추기	
				} else {
					break;
				}
			}	
		}
		System.out.println(cnt);
	}
}