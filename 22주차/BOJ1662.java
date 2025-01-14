import java.io.*;
import java.util.*;


public class BOJ1662 {
	static String encoded;
	static int depth;
	static int getLength(String s) {
        Stack<Integer> stack = new Stack<>();
        int length = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (Character.isDigit(c)) {
                if (i + 1 < s.length() && s.charAt(i + 1) == '(') {
                    stack.push(length);
                    stack.push(c - '0');
                    length = 0;
                } else {
                    length++;
                }
            } else if (c == ')') {
                int repeat = stack.pop();
                int prevLength = stack.pop();
                length = prevLength + repeat * length;
            } else if (c != '(') {
                length++;
            }
        }
       return length;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(getLength(br.readLine()));
	}

}
