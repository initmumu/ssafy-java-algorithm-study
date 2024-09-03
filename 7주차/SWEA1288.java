import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class SWEA1288 {
	
	public static int N;
	
	public static int solution () {
        HashSet<Character> set = new HashSet<>();
        int n = 0;
        while (set.size() < 10) {
            String num = String.valueOf(++n * N);
            for (char ch : num.toCharArray()) {
                set.add(ch);
            }
        }
        return n;
	}

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			System.out.printf("#%d %d\n", t, solution()*N);
		}

	}

}
