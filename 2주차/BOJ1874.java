import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        StringBuilder sb = new StringBuilder();

        int prevN = 0;
        for(int i = 0; i < n; i++){
            int curN = Integer.parseInt(br.readLine());

            while(prevN < curN){
                stack.add(++prevN);
                sb.append('+').append('\n');
            }

            if(stack.peek() == curN){
                stack.pop();
                sb.append('-').append('\n');
            }
            else{
                System.out.print("NO");
                return;
            }
        }

        System.out.print(sb);
    }
}
