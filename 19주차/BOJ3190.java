import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int N, K;
	static int[][] board;
	// 우 하 좌 상
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};
	
	// 범위 밖인가 & 뱀이랑 부딪히는가
	static boolean isOut(int x, int y) {
		if (x >= 0 && x < N && y >= 0 && y < N && board[x][y] != 2) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// 보드의 크기
		N = Integer.parseInt(br.readLine());
		// 사과의 개수
		K = Integer.parseInt(br.readLine());
		
		// 보드
		board = new int[N][N];
		
		// 사과는 1로 표시
		for (int k = 0; k < K; k++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			board[x][y] = 1;
		}
		
		// 뱀은 2로 표시
		board[0][0] = 2;
		// 뱀 머리 위치
		int[] head = {0, 0};
		// 뱀 위치 정보
		Queue<int[]> snake = new ArrayDeque<int[]>();
		snake.add(new int[] {0, 0});
		
		// 뱀의 방향 변환 정보 저장
		// 1 : 오른쪽 회전, -1 : 왼쪽 회전
		int L = Integer.parseInt(br.readLine());
		Queue<int[]> changes = new ArrayDeque<int[]>();
		
		for (int l = 0; l < L; l++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			String C = st.nextToken();
			
			if (C.equals("D")) {
				int[] temp = {X, 1};
				changes.add(temp);
			} else {
				int[] temp = {X, -1};
				changes.add(temp);
			}
		}
		
		// 정답 : 시간
		int ans = 0;
		// 현재 방향
		int way = 0;
		
		while (true) {
			
			ans++;
			
			// 일단 머리 움직이기
			head[0] += dx[way];
			head[1] += dy[way];
			
			// 벽이거나 뱀이랑 부딪히면 종료
			if (isOut(head[0], head[1]) == true) {
				break;
			}
			
			// 그냥 빈 칸이면 꼬리 움직이기
			if (board[head[0]][head[1]] == 0) {
				int[] tail = snake.poll();
				board[tail[0]][tail[1]] = 0;
			}
			
			// 사과면 먹어버려
			if (board[head[0]][head[1]] == 1) {
				board[head[0]][head[1]] = 0;
			}
			
			board[head[0]][head[1]] = 2;
			snake.add(new int[] {head[0], head[1]});
			
			// 방향 회전 타이밍이면 고고
			if (changes.size() > 0 && ans == changes.peek()[0]) {
				int[] change = changes.poll();
				way = (way + change[1] + 4) % 4;
			}
		}
		
		System.out.println(ans);
	}
}