import java.io.*;

public class Main {
    static int cmd, c;
    static int[] queue = new int[2_000_000];
    static int size = 0;
    static int front = -1;
    static int rear = -1;

    public static void main(String[] args) throws IOException {
        int numCmd = readInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numCmd; i++) {
            switch (readCmd()) {
                case 195:
                    if (size == 0) sb.append(-1).append("\n");
                    else sb.append(queue[rear]).append('\n');
                    break;
                case 216:
                    if (size == 0) sb.append(-1).append("\n");
                    else sb.append(queue[(front + 1) % 2_000_000]).append('\n');
                    break;
                case 210:
                    if (size == 0) sb.append(1).append("\n");
                    else sb.append(0).append("\n");
                    break;
                case 220:
                    sb.append(size).append('\n');
                    break;
                case 223:
                    if (size == 0) sb.append(-1).append('\n');
                    else {
                        front = (front + 1) % 2_000_000;
                        sb.append(queue[front]).append('\n');
                        size--;
                    }
                    break;
                case 229:
                    if (size <= 2_000_000) {
                        rear = (rear + 1) % 2_000_000;
                        queue[rear] = readInt();
                        size++;
                    }
                    break;
            }
        }
        System.out.println(sb);
    }

    public static int readCmd() throws IOException {
        cmd = System.in.read() + System.in.read();
        while (System.in.read() > 96) {}
        return cmd;
    }

    public static int readInt() throws IOException {
        cmd = 0;
        while((c = System.in.read()) > 47) cmd = (cmd << 3) + (cmd << 1) + (c & 15);
        return cmd;
    }
}
