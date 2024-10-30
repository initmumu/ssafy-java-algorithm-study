import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, R, C;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		int W = (int)Math.pow(2, N);
		
		int mid = W / 2;
		
		int i = 0;
		
		System.out.print(solution(N, R, C));
	}
	
	public static int solution(int n, int r, int c) {
		if(n == 0) return 0;
		int mid = (int)Math.pow(2, n - 1);
		if(r < mid && c < mid) {
			return solution(n - 1, r, c);
		}
		else if(r < mid && c >= mid) {
			return (mid * mid) + solution(n - 1, r, c - mid);
		}
		else if(r >= mid && c < mid) {
			return 2 * (mid * mid) + solution(n - 1, r - mid, c);
		}
		else if(r >= mid && c >= mid) {
			return 3 * (mid * mid) + solution(n - 1, r - mid, c - mid);
		}
		
		return 0;
	}

}
