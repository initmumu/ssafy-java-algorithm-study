import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class BOJ1475 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] plasticNumSets = new int[10];
		int[] roomNumber = Stream.of(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
		int min = 0, cur = 0;

		for(int number : roomNumber) {
			if(number == 6 || number == 9) {
				number = plasticNumSets[6] > plasticNumSets[9] ? 6 : 9;
			}
			cur = --plasticNumSets[number];
			if(cur < min) min = cur;
		}

		System.out.print(-1 * min);
	}
}