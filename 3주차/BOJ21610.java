package algo_study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21610 {
	
	public static int N, M;
	public static int[][] graph;
	public static int[][] cloud;
	public static final int[] dx = {-1, -1, 1, 1};
	public static final int[] dy = {-1, 1, -1, 1};
	public static int[] nx;
	public static int[] ny;
	
	
	public static int calcMinus(int num) {
		if (num > N) {
			return num % N;
		}
		return num;
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
	
	public static void FirstCloudMove(int d, int s) {
		nx = new int[]{N-1, N-1, N, N};
		ny = new int[]{1, 2, 1, 2};
		switch(d) {
			case 1:
				for (int k = 0; k < 4; k++) {
					ny[k] = calcMinus(N + (ny[k] - ((s % N))));
				}
				break;
			case 2:
				for (int k = 0; k < 4; k++) {
					nx[k] = calcMinus(N + (nx[k] - ((s % N))));
					ny[k] = calcMinus(N + (ny[k] - ((s % N))));
				}
				break;
			case 3:
				for (int k = 0; k < 4; k++) {
					nx[k] = calcMinus(N + (nx[k] - ((s % N))));
				}
				break;
			case 4:
				for (int k = 0; k < 4; k++) {
					nx[k] = calcMinus(N + (nx[k] - ((s % N))));
					ny[k] = calcMinus(ny[k] + (s % N));
				}
				break;
			case 5:
				for (int k = 0; k < 4; k++) {
					ny[k] = calcMinus(ny[k] + (s % N));
				}
				break;
			case 6:
				for (int k = 0; k < 4; k++) {
					nx[k] = calcMinus(nx[k] + (s % N));
					ny[k] = calcMinus(ny[k] + (s % N));
				}
				break;
			case 7:
				for (int k = 0; k < 4; k++) {
					nx[k] = calcMinus(nx[k] + (s % N));
				}
				break;
			case 8:
				for (int k = 0; k < 4; k++) {
					nx[k] = calcMinus(nx[k] + (s % N));
					ny[k] = calcMinus(N + (ny[k] - ((s % N))));
				}
				break;
			
		}
//		구름에 있는 물 1씩 증가
		for (int k = 0; k < 4; k++) {
			graph[nx[k]][ny[k]]++;
		}
		
//		구름 대각선 물 있으면 1씩 누적해서 증가
		for (int k = 0; k < 4; k++) {
			graph[nx[k]][ny[k]] += diag(nx[k], ny[k]);
		}

		// 구름에 존재하는 물 temp 배열에 저장 후 0으로 초기화 (밑 반복문 조건 피하기 위해)
		int [] temp = new int[4];
		for (int k = 0; k < 4; k++) {
			temp[k] = graph[nx[k]][ny[k]];
			graph[nx[k]][ny[k]] = 0;
		}
		
//		첫 번째 구름을 제외한 나머지 2이상 이면 구름 체크 및 물 2 감소
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (graph[i][j] >= 2) {
					cloud[i][j] = 1;
					graph[i][j] -= 2;
				}
			}
		}
		for (int k = 0; k < 4; k++) {
			graph[nx[k]][ny[k]] = temp[k];
		}
	}
	
	public static void cloudMove(int d, int s) {
//		이동할 구름 저장 위한 임시 배열
		int [][] temp = new int [N+1][N+1];
		switch(d) {
			case 1:
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (cloud[i][j] > 0) {
							int ny = calcMinus(N + (j - (s % N)));
							temp[i][ny]++;
						}
					}
				}
				break;
			case 2:
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (cloud[i][j] > 0) {
							int nx = calcMinus(N + (i - (s % N)));
							int ny = calcMinus(N + (j - (s % N)));
							temp[nx][ny]++;
						}
					}
				}
				break;
			case 3:
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (cloud[i][j] > 0) {
							int nx = calcMinus(N + (i - (s % N)));
							temp[nx][j]++;
						}
					}
				}
				break;
			case 4:
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (cloud[i][j] > 0) {
							int nx = calcMinus(N + (i - (s % N)));
							int ny = calcMinus(j + (s % N));
							temp[nx][ny]++;
						}
					}
				}
				break;
			case 5:
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (cloud[i][j] > 0) {
							int ny = calcMinus(j + (s % N));
							temp[i][ny]++;
						}
					}
				}
				break;
			case 6:
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (cloud[i][j] > 0) {
							int nx = calcMinus(i + (s % N));
							int ny = calcMinus(j + (s % N));
							temp[nx][ny]++;
						}
					}
				}
				break;
			case 7:
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (cloud[i][j] > 0) {
							int nx = calcMinus(i + (s % N));
							temp[nx][j]++;
						}
					}
				}
				break;
			case 8:
				for (int i = 1; i <= N; i++) {
					for (int j = 1; j <= N; j++) {
						if (cloud[i][j] > 0) {
							int nx = calcMinus(i + (s % N));
							int ny = calcMinus(N + (j - (s % N)));
							temp[nx][ny]++;
						}
					}
				}
				break;
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
				if (cloud[i][j] > 0) {
					graph[i][j]++;
				}
			}
		}
		
//		대각선 검사 후 카운트
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j] > 0) {
					graph[i][j] += diag(i, j);
				}
			}
		}
		
		
//		비 2씩 감소하는 위치 구름 저장 위해서 임시 배열2
		int [][] temp2 = new int [N+1][N+1];
		
//		2 감소 및 감소된 구름 새로운 구름에 저장
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (cloud[i][j] > 0) {
					continue;
				}
				if (graph[i][j] >= 2) {
					temp2[i][j] = 1;
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
		cloud = new int [N+1][N+1];

		st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		FirstCloudMove(d, s);
		
		for (int i = 2; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			d = Integer.parseInt(st.nextToken());
			s = Integer.parseInt(st.nextToken());
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
