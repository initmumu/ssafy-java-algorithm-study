import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int[] stack = new int[10001];
        stack[0] = -1;
        int curIndex = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            switch(st.nextToken()) {
                case "push":
                    stack[++curIndex] = Integer.parseInt(st.nextToken());
                    break;
                case "pop":
                    sb.append(stack[curIndex]).append('\n');
                    if(curIndex > 0) curIndex--;
                    break;
                case "top":
                    sb.append(stack[curIndex]).append('\n');
                    break;
                case "size":
                    sb.append(curIndex).append('\n');
                    break;
                case "empty":
                    if(curIndex <= 0) sb.append(1).append('\n');
                    else sb.append(0).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }
}