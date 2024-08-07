import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21610 {
	
	public static int N, M;
	public static int[][] graph;
	public static boolean[][] cloud;
	public static final int[] dx = {-1, -1, 1, 1};
	public static final int[] dy = {-1, 1, -1, 1};
	public static final int[] new_dx = {0, -1, -1, -1, 0, 1, 1, 1};
	public static final int[] new_dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	
	
	public static int calc(int num) {
		if (num % N == 0) {
			return N;
		}
		return num % N;
	}
	
	
	public static int diag(int i, int j) {
		int count = 0;
		for (int k = 0; k < 4; k++) {
			int nx2 = i + dx[k];
			int ny2 = j + dy[k];
			if (nx2 > 0 && nx2 <= N && ny2 > 0 && ny2 <= N) {
				if (graph[nx2][ny2] > 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void cloudMove(int d, int s) {
//		이동할 구름 저장 위한 임시 배열
		boolean [][] temp = new boolean [N+1][N+1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j]) {
					int nx = calc(N + i + new_dx[d-1] * (s % N));
					int ny = calc(N + j + new_dy[d-1] * (s % N));
					temp[nx][ny] = true;
				}
			}
		}
//		이동한 구름 저장
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cloud[i][j] = temp[i][j];
			}
		}
		
//		구름 위치 비 1씩 증가
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j]) {
					graph[i][j]++;
				}
			}
		}
		
//		대각선 검사 후 카운트
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j]) {
					graph[i][j] += diag(i, j);
				}
			}
		}
		
		
//		비 2씩 감소하는 위치 구름 저장 위해서 임시 배열2
		boolean [][] temp2 = new boolean [N+1][N+1];
		
//		2 감소 및 감소된 구름 새로운 구름에 저장
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j]) {
					continue;
				}
				if (graph[i][j] >= 2) {
					temp2[i][j] = true;
					graph[i][j] -= 2;
				}
			}
		}
		
//		새로운 구름 위치 저장
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cloud[i][j] = temp2[i][j];
			}
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new int [N+1][N+1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cloud = new boolean [N+1][N+1];
		cloud[N-1][1] = cloud[N-1][2] = cloud[N][1] = cloud[N][2] = true;
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			cloudMove(d, s);
		}
		int result = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				result += graph[i][j];
			}
		}
		System.out.println(result);

	}

}
