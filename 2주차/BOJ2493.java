import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Tower{
        int index;
        int height;

        Tower(int index, int height){
            this.index = index;
            this.height = height;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Tower[] stack = new Tower[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int top = 0;

        int n = Integer.parseInt(st.nextToken());
        sb.append(0).append(' ');
        stack[top] = new Tower(1, n);

        for(int i = 1; i <= N; i++){
            n = Integer.parseInt(st.nextToken());

            while(stack[top].height < n){
                if(top == 0) {
                    sb.append(0).append(' ');
                    stack[top] = new Tower(i, n);
                    break;
                }
                else{
                    top--;
                }
            }

            if(stack[top].height > n) {
                sb.append(stack[top].index).append(' ');
                stack[++top] = new Tower(i, n);
            }
        }
        System.out.print(sb);
    }
}
