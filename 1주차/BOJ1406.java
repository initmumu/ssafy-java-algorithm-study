import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Stack<Character> leftStack = new Stack<>();
		Stack<Character> rightStack = new Stack<>();
		
		String letters = br.readLine();
		
		for (int i = 0; i < letters.length(); i++) {
			leftStack.push(letters.charAt(i));
		}
		
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			String x = br.readLine();
			char a = x.charAt(0);
			
			if (a == 'L' && !leftStack.isEmpty()) {
				rightStack.push(leftStack.pop());
			}
			if (a == 'D' && !rightStack.isEmpty()) {
				leftStack.push(rightStack.pop());
			}
			if (a == 'B' && !leftStack.isEmpty()) {
				leftStack.pop();
			}
			if (a == 'P') {
				Character b = x.charAt(2);
				leftStack.push(b);
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
		
		System.out.println(sb.toString());

	}

}