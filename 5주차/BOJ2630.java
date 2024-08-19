import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2630 {
	
	public static int N;
	public static int white;
	public static int blue;
	public static int [][] graph;
	
	public static boolean checkColorSame(int y, int x, int n) {
		int color = graph[y][x];
		
		for (int i = y; i < y + n; i++) {
			for (int j = x; j < x + n; j++) {
				if(graph[i][j] != color) return false;
			}
		}
		return true;
	}
	
	public static void partition_four(int y, int x, int n) {
		if (checkColorSame(y, x, n)) {
			int color = graph[y][x];
			if (color == 0) {
				white++;
			} else {
				blue++;
			}
			return;
		}
		
		n = n / 2;
		
//		if (n <= 0) return;
//		1사분면
		partition_four(y, x+n, n);
//		2사분면
		partition_four(y, x, n);
//		3사분면
		partition_four(y+n, x, n);
//		4사분면
		partition_four(y+n, x+n, n);
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		graph = new int [N][N];
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		white = 0;
		blue = 0;
		partition_four(0, 0, N);
		System.out.println(white);
		System.out.println(blue);

	}

}
