import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arrS = br.readLine().toCharArray();
        Stack<Integer> stack = new Stack<>();
        stack.push(1);

        int result = 0;
        for (int i = 0; i < arrS.length; i++) {
            if (arrS[i] == '(') {
                result -= stack.peek();
                stack.push(stack.peek() * (arrS[i - 1] - '0'));
            }
            else if (arrS[i] == ')') {
                stack.pop();
            }
            else {
                result += stack.peek();
            }
        }

        System.out.println(result);
    }
}
