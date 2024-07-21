import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int i = 0; i < TC; i++) {
            Stack<Character> lStack = new Stack<Character>();
            Stack<Character> rStack = new Stack<Character>();
            char[] keyLog = br.readLine().toCharArray();
            for (char key : keyLog) {
                switch (key) {
                    case '<':
                        if(!lStack.isEmpty()) rStack.push(lStack.pop());
                        break;
                    case '>':
                        if(!rStack.isEmpty()) lStack.push(rStack.pop());
                        break;
                    case '-':
                        if(!lStack.isEmpty()) lStack.pop();
                        break;
                    default:
                        lStack.push(key);
                }
            }

            StringBuilder sb = new StringBuilder();
            int rs = rStack.size();
            for (char c: lStack) sb.append(c);
            for (int t = 0; t < rs; t++) sb.append(rStack.pop());

            System.out.println(sb);
        }
    }
}
