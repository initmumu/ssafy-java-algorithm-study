import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] queue = new int[2000000];

        int front = 0, back = 0;
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String inst = st.nextToken();

            switch(inst) {
                case "push":
                    int n = Integer.parseInt(st.nextToken());
                    queue[back++] = n;
                    break;
                case "pop":
                    if(back - front == 0) sb.append(-1).append('\n');
                    else sb.append(queue[front++]).append('\n');
                    break;
                case "size":
                    sb.append(back - front).append('\n');
                    break;
                case "empty":
                    if(back - front == 0) sb.append(1).append('\n');
                    else sb.append(0).append('\n');
                    break;
                case "front":
                    if(back - front == 0) sb.append(-1).append('\n');
                    else sb.append(queue[front]).append('\n');
                    break;
                case "back":
                    if(back - front == 0) sb.append(-1).append('\n');
                    else sb.append(queue[back - 1]).append('\n');
                    break;
            }
        }
        System.out.println(sb);
    }

}
