import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String inputs = br.readLine();
			
			Stack<Character> leftStack = new Stack<>();
			Stack<Character> rightStack = new Stack<>();
			
			for (int i = 0; i < inputs.length(); i++) {
				char c = inputs.charAt(i);
				
				if (c == '<') {
					if (leftStack.size() >= 1) {
					rightStack.push(leftStack.pop());
					}
				} else if (c == '>') {
					if (rightStack.size() >= 1) {
					leftStack.push(rightStack.pop());
					}
				} else if (c == '-') {
					if (leftStack.size() >= 1) {
					leftStack.pop();
					}
				} else {
					leftStack.push(c);
				}
			}
			
			Stack<Character> tempStack = new Stack<>(); 
			StringBuilder sb = new StringBuilder();
			
			while (!leftStack.isEmpty()) {
				tempStack.push(leftStack.pop());
			}
			while (!tempStack.isEmpty()) {
				sb.append(tempStack.pop());
			}
			
			while (!rightStack.isEmpty()) {
				sb.append(rightStack.pop());
			}
			
			bw.write(sb.toString());
            bw.newLine();
            }
		bw.flush();
        bw.close();
        br.close();
	}

}
