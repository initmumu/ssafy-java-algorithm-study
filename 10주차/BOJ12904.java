import java.io.*;

public class BOJ12904 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringBuilder S = new StringBuilder(br.readLine());
		StringBuilder T = new StringBuilder(br.readLine());
		
		while (S.length() < T.length()) {
			if (T.charAt(T.length() - 1) == 'A') {
				T.deleteCharAt(T.length() - 1);
			}
			else {
				T.deleteCharAt(T.length() - 1);
				T.reverse();
			}
		}
		
		System.out.println(S.toString().equals(T.toString()) ? 1 : 0);
	}
}
