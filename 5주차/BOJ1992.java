import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1992 {
	
	public static int[][] graph;
	public static int N;
	public static StringBuilder sb;
	
	public static boolean checkColorSame(int y, int x, int n) {
		int color = graph[y][x];
		
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if (graph[i][j] != color) return false;
			}
		}
		
		return true;
	}
	
	public static void partition_four(int y, int x, int n) {
		if (checkColorSame(y, x, n)) {
			if (graph[y][x] == 0) {
				sb.append("0");
			} else {
				sb.append("1");
			}
			return;
		}
		sb.append("(");
		
		n = n / 2;
//		2사분면
		partition_four(y, x, n);
//		1사분면
		partition_four(y, x+n, n);
//		3사분면
		partition_four(y+n, x, n);
//		4사분면
		partition_four(y+n, x+n, n);
		sb.append(")");
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		graph = new int [N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			String[] s = br.readLine().split("");
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(s[j]);
			}
		}
		sb = new StringBuilder();
		partition_four(0, 0, N);
		System.out.println(sb);
	}

}
