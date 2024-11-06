import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

class Coor {
	int x;
	int y;
	int cnt;
	int keys;
	
	public Coor(int x, int y, int cnt, int keys) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
		this.keys = keys;
	}
}

public class Main {
	
	static Queue<Coor> queue;
	static int N, M, ans;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, -1, 0, 1};
	static char[][] maze;
	static ArrayList<Integer>[][] visited;
	
	// 미로 밖인지 확인
	static boolean isIn(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < M) {
			return true;
		}
		return false;
	}
	
	// 열쇠 위치인지 확인
	static boolean isKey(int x, int y) {
		if (maze[x][y] == 'a' || maze[x][y] == 'b' || maze[x][y] == 'c' || maze[x][y] == 'd' || maze[x][y] == 'e' || maze[x][y] == 'f' ) {
			return true;
		} 
		return false;
	}
	
	// 문 위치인지 확인
	static boolean isDoor(int x, int y) {
		if (maze[x][y] == 'A' || maze[x][y] == 'B' || maze[x][y] == 'C' || maze[x][y] == 'D' || maze[x][y] == 'E' || maze[x][y] == 'F' ) {
			return true;
		} 
		return false;
	}
	
	// 방문처리
	static void markVisited(Coor coor) {
		visited[coor.x][coor.y].add(coor.keys);
	}

	// 문 딸 수 있나
	static boolean canGo(int s, int c) {
		if ((s & (1<<c)) > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	// 방문 가능하나
	static boolean isVisited(int x, int y, int keys) {	
		for (int k : visited[x][y]) {
			if (k == keys) {
				return true;
			}
		}
		return false;
	}
	
	
	static void bfs() {
		
		// 큐 비었으면 out
		if (queue.size() == 0) {
			return;
		}
		
		Coor coor = queue.poll();
		
		for (int d = 0; d < 4; d++) {
			int nx = coor.x + dx[d];
			int ny = coor.y + dy[d];
			if (isIn(nx, ny) && !isVisited(nx, ny, coor.keys)) {		
				// 빈 칸이면 큐에 추가
				if (maze[nx][ny] == '.' || maze[nx][ny] == '0') {
					Coor co = new Coor(nx, ny, coor.cnt + 1, coor.keys);
					markVisited(co);
					queue.add(co);
				// 출구면 out
				} else if (maze[nx][ny] == '1') {
					ans = coor.cnt + 1;
					return;
				// 열쇠면 주워버려
				} else if (isKey(nx, ny)) {
					if (maze[nx][ny] == 'a') {
						int t = coor.keys | (1<<0);
						Coor co = new Coor(nx, ny, coor.cnt + 1, t);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'b') {
						int t = coor.keys | (1<<1);
						Coor co = new Coor(nx, ny, coor.cnt + 1, t);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'c') {
						int t = coor.keys | (1<<2);
						Coor co = new Coor(nx, ny, coor.cnt + 1, t);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'd') {
						int t = coor.keys | (1<<3);
						Coor co = new Coor(nx, ny, coor.cnt + 1, t);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'e') {
						int t = coor.keys | (1<<4);
						Coor co = new Coor(nx, ny, coor.cnt + 1, t);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'f') {
						int t = coor.keys | (1<<5);
						Coor co = new Coor(nx, ny, coor.cnt + 1, t);
						markVisited(co);
						queue.add(co);
					}
				// 문이고 열쇠 있으면 고고
				} else if (isDoor(nx, ny)) {
					if (maze[nx][ny] == 'A' && canGo(coor.keys, 0)) {
						Coor co = new Coor(nx, ny, coor.cnt + 1, coor.keys);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'B' && canGo(coor.keys, 1)) {
						Coor co = new Coor(nx, ny, coor.cnt + 1, coor.keys);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'C' && canGo(coor.keys, 2)) {
						Coor co = new Coor(nx, ny, coor.cnt + 1, coor.keys);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'D' && canGo(coor.keys, 3)) {
						Coor co = new Coor(nx, ny, coor.cnt + 1, coor.keys);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'E' && canGo(coor.keys, 4)) {
						Coor co = new Coor(nx, ny, coor.cnt + 1, coor.keys);
						markVisited(co);
						queue.add(co);
					} else if (maze[nx][ny] == 'F' && canGo(coor.keys, 5)) {
						Coor co = new Coor(nx, ny, coor.cnt + 1, coor.keys);
						markVisited(co);
						queue.add(co);
					}
				}
			}
		}
		bfs();		
	}

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 미로 세로 크기
		N = Integer.parseInt(st.nextToken());
		// 미로 가로 크기
		M = Integer.parseInt(st.nextToken());
		
		int x = 0;
		int y = 0;
		
		// 미로
		maze = new char[N][M];
		for (int i = 0; i < N; i++) {
			String temp = br.readLine();
			for (int j = 0; j < M; j++) {
				char t = temp.charAt(j);
				maze[i][j] = t;
				if (t == '0') {
					x = i;
					y = j;
				}
			}
		}
				
		// 방문 여부 저장
		visited = new ArrayList[N][M];
		for (int i = 0; i < N; i++) {
		    for (int j = 0; j < M; j++) {
		        visited[i][j] = new ArrayList<Integer>();
		        visited[i][j].add(-1);
		    }
		}

		// 큐 선언 및 초기값 넣기
		queue = new ArrayDeque<>();
		Coor coor = new Coor(x, y, 0, 0);
		queue.add(coor);
		
		// 정답 초기화
		ans = -1;
		
		bfs();
		
		System.out.println(ans);
	}
}