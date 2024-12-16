import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ7662 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			int K = Integer.parseInt(br.readLine());
			TreeMap<Integer, Integer> treeMap = new TreeMap<>();

			for (int i = 0; i < K; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String command = st.nextToken();
				int n = Integer.parseInt(st.nextToken());

				if (command.equals("I")) {
					treeMap.put(n, treeMap.getOrDefault(n, 0) + 1);
				} else if (command.equals("D")) {
					if (treeMap.isEmpty()) continue;

					if (n == 1) {
						int max = treeMap.lastKey();
						if (treeMap.get(max) == 1) {
							treeMap.remove(max);
						} else {
							treeMap.put(max, treeMap.get(max) - 1);
						}
					} else {
						int min = treeMap.firstKey();
						if (treeMap.get(min) == 1) {
							treeMap.remove(min);
						} else {
							treeMap.put(min, treeMap.get(min) - 1);
						}
					}
				}
			}

			if (treeMap.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				System.out.println(treeMap.lastKey() + " " + treeMap.firstKey());
			}
		}
	}
}
