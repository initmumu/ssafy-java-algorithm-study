import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, Exception {
        int N = 0, t;
        while((t=System.in.read())>47){N = N*10+(t-'0');}
        if(t==13) System.in.read();
        int[] stack = new int[10001];
        int size = 0;

        StringBuilder sb = new StringBuilder();
        int inst;
        while(N-- > 0) {
            inst = System.in.read() + System.in.read();
            while ((t=System.in.read()) > 96) {}
            if (t == 13) System.in.read();
            switch (inst) {
                case 229:
                    inst = 0;
                    while((t=System.in.read())>47){inst = inst*10+(t-'0');}
                    if(t==13) System.in.read();
                    stack[size++] = inst;
                    break;
                case 227:
                    if (size == 0) sb.append("-1\n");
                    else sb.append(stack[size - 1]).append("\n");
                    break;
                case 220:
                    sb.append(size).append("\n");
                    break;
                case 210:
                    if (size == 0) sb.append("1\n");
                    else sb.append("0\n");
                    break;
                case 223:
                    if (size == 0) sb.append("-1\n");
                    else sb.append(stack[--size]).append("\n");
                    break;
            }
        }
        System.out.println(sb);
    }
}
