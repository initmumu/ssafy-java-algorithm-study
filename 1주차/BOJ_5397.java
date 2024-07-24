import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class BOJ_5397 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int L = Integer.parseInt(br.readLine());
		for (int i = 0; i < L; i++) {
			LinkedList<Character> list = new LinkedList<>(); 
			String keyLogger = br.readLine();
			int current = 0;
			for (int j = 0; j < keyLogger.length(); j++) {
				if (keyLogger.charAt(j) == '<') {
					if (current > 0) current--; 
				}
				else if (keyLogger.charAt(j) == '>') {
					if (current < list.size()) current++;
				}
				else if (keyLogger.charAt(j) == '-') {
					if (!list.isEmpty() && current != 0) list.remove(--current);
				}
				else if (current == list.size()) {
					list.add(keyLogger.charAt(j));
					current++;
				}
				else {
					list.add(current++, keyLogger.charAt(j));
				};
			}
			
			StringBuffer sb = new StringBuffer();
			for (Character character : list) {
				sb.append(character);
			}
			
			System.out.println(sb.toString());
			
		}
	}
}
