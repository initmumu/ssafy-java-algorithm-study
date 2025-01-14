import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ1662 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Stack<Integer> lengthStack = new Stack<>();
        Stack<Integer> repeatStack = new Stack<>();
        int currentLength = 0;
        int i = 0;

        while (i < s.length()) {
            char c = s.charAt(i);

            if (c >= '0' && c <= '9') {
                int num = c - '0';
                if (i + 1 < s.length() && s.charAt(i + 1) == '(') {
                    lengthStack.push(currentLength);
                    repeatStack.push(num);
                    currentLength = 0;
                    i++;
                } else {
                    currentLength++;
                }
            } else if (c == ')') {
                int repeat = repeatStack.pop();
                currentLength = lengthStack.pop() + currentLength * repeat;
            } else {
                currentLength++;
            }
            i++;
        }

        System.out.println(currentLength);
    }
}
