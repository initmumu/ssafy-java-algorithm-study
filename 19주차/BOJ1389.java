import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
		
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 유저의 수
		int N = Integer.parseInt(st.nextToken());
		// 친구 관계의 수
		int M = Integer.parseInt(st.nextToken());
		
		// 인접 배열
		int[][] arr = new int[N + 1][N + 1];
		
		int INF = 987654321;
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				if (i == j) {
					arr[i][j] = 0;
				} else {
					arr[i][j] = INF;
				}
			}
		}
		
		// 관계 정보 저장하기
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[a][b] = 1;
			arr[b][a] = 1;
		}
		
		// 플로이드-와샬
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
		
		int minSum = INF;
		int ans = -1;
		
		for (int i = 1; i < N + 1; i++) {
			int sum = 0;
			for (int j = 1; j < N + 1; j++) {
				sum += arr[i][j];
			}
			
			if (minSum > sum) {
				minSum = sum;
				ans = i;
			}
		}
		
		System.out.println(ans);
	}
}