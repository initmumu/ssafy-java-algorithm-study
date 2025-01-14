import java.io.IOException;

public class BOJ1904 {

	public static void main(String[] args) throws IOException {
		int N = read();
		if (N == 1) System.out.println(1);
		else if (N == 2) System.out.println(2);
		else {
			int prev = 2, pprev = 1;
			for (int i = 2; i < N; i++) {
				int temp = prev % 15746;
				prev = temp + pprev % 15746;
				pprev = temp;
			}
			System.out.println(prev % 15746);
		}

	}
	
	public static int read() throws IOException {
		int c, t = 0;
		while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
		return t;
	}

}
