import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ3273 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine());
		List<Integer> arr = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) arr.add(Integer.parseInt(st.nextToken()));

		int x = Integer.parseInt(br.readLine());
		int result = 0;
		int left = 0, right = arr.size() - 1;

		Collections.sort(arr);

		while(left < right) {
			if(x - arr.get(left) < arr.get(right)) {
				right--;
				continue;
			}

			if(x - arr.get(left) == arr.get(right)) result++;
			left++;
		}

		System.out.print(result);
	}
}
