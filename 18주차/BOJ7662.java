package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			
			int k = Integer.parseInt(br.readLine());
			
			TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
			
			for (int i = 0; i < k; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String m = st.nextToken();
				int n = Integer.parseInt(st.nextToken());
				
				// 삽입
				if (m.equals("I")) {
					if (map.containsKey(n)) {
						map.replace(n, map.get(n) + 1);
					} else {
						map.put(n, 1);
					}
				// 최댓값 삭제
				} else if (m.equals("D") && n == 1 && map.size() > 0) {
					if (map.get(map.lastKey()) == 1) {
						map.remove(map.lastKey());
					} else {
						map.replace(map.lastKey(), map.get(map.lastKey()) - 1);
					}
				// 최솟값 삭제
				} else if (m.equals("D") && n == -1 && map.size() > 0) {
					if (map.get(map.firstKey()) == 1) {
						map.remove(map.firstKey());
					} else {
						map.replace(map.firstKey(), map.get(map.firstKey()) - 1);
					}
				}	
			}
			
			if (map.isEmpty()) {
				System.out.println("EMPTY");
			} else {
				int min = map.firstKey();
				int max = map.lastKey();
				System.out.println(max + " " + min);
			}
		}
	}
}