import java.io.*;
import java.util.*;

public class BOJ2141 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		List<int[]> villages = new ArrayList<>();
		
		long sumPopulation = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int X = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			
			sumPopulation += A;
			villages.add(new int[] {X, A});
		}
		
		Collections.sort(villages, (o1, o2) -> Integer.compare(o1[0], o2[0]));

		long mid = (sumPopulation + 1) / 2;
		
		long curSum = 0;
		for (int[] village : villages) {
			curSum += village[1];
			
			if (curSum >= mid) {
				System.out.println(village[0]);
				break;
			}
		}
	}
}
