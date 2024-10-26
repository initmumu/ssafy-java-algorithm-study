import java.io.*;
import java.util.*;

public class BOJ2212 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		
		int[] sensorPos = new int[N];
		int[] distDiff = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			sensorPos[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(sensorPos);
		
		for (int i = 1; i < N; i++) {
			distDiff[i] = sensorPos[i] - sensorPos[i - 1];
		}
		Arrays.sort(distDiff);

		int sum = 0;
		for (int i = 0; i <= N - K; i++) {
			sum += distDiff[i];
		}
		
		System.out.println(sum);
	}
}
