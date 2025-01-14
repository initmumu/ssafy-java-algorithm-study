import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        Stack<Integer> values = new Stack<>();
        Stack<Integer> muls = new Stack<>();

        int x = 0;
        char prev = '0';
        for(char c : s.toCharArray()) {
            if(c == '('){
                muls.push(prev - '0');
                values.add(x - 1);
                values.add(-1);
                x = 0;
                continue;
            }

            if(c == ')'){
                int n = x;
                int sum = 0;
                while(n >= 0){
                    sum += n;
                    n = values.pop();
                }

                values.add(sum * muls.pop());
                x = 0;
                continue;
            }

            x++;
            prev = c;
        }

        int result = x;

        for(int n : values){
            result += n;
        }
        System.out.println(result);
    }
}
