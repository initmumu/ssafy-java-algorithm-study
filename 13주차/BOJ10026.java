package algorithm;

import java.io.*;
import java.util.*;

class Coor {
	int x;
	int y;
	
	public Coor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static int N;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static char[][] arrS, arrN;
	
	// 범위 내 확인
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N) {
			return true;
		}
		return false;
	}
	
	// 적록색약인 경우 넓이 우선 탐색
	static void bfsS(int x, int y) {
		
		Queue<Coor> queue = new ArrayDeque<>();
		queue.add(new Coor(x, y));
		char temp = arrS[x][y];
		arrS[x][y] = 'N';
		
		while (!queue.isEmpty()) {
			Coor coor = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = coor.x + dx[d];
				int ny = coor.y + dy[d];
				if (isIn(nx, ny) && arrS[nx][ny] == temp) {
					queue.add(new Coor(nx, ny));
					arrS[nx][ny] = 'N';
				}
			}
		}	
	}
	
	// 적록색약이 아닌 경우 넓이 우선 탐색
	static void bfsN(int x, int y) {
		
		Queue<Coor> queue = new ArrayDeque<>();
		queue.add(new Coor(x, y));
		char temp = arrN[x][y];
		arrN[x][y] = 'N';
		
		while (!queue.isEmpty()) {
			Coor coor = queue.poll();
			for (int d = 0; d < 4; d++) {
				int nx = coor.x + dx[d];
				int ny = coor.y + dy[d];
				if (isIn(nx, ny) && arrN[nx][ny] == temp) {
					queue.add(new Coor(nx, ny));
					arrN[nx][ny] = 'N';
				}
			}
		}	
	}

	public static void main(String[] args) throws Exception{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		
		// 적록색약 버전 배열
		arrS = new char[N][N];
		// 적록색약 아닌 배열
		arrN = new char[N][N];
		
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < N; j++) {
				arrN[i][j] = temp.charAt(j);
				if (arrN[i][j] == 'G') {
					arrS[i][j] = 'R';
				} else {
					arrS[i][j] = arrN[i][j];
				}
			}
		}
		
		// 적록색약 구역 개수
		int cntS = 0;
		// 적록색약 구역 아닌 개수
		int cntN = 0;
		
		// 적록색약 아닌 경우 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arrN[i][j] != 'N') {
					bfsN(i, j);
					cntN++;
				}
			}
		}
		
		// 적록색약인 경우 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arrS[i][j] != 'N') {
					bfsS(i, j);
					cntS++;
				}
			}
		}
		
		System.out.println(cntN + " " + cntS);
		
	}
}