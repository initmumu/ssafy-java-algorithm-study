import java.io.*;

public class Main {
    static int cmd, c;
    static int[] queue = new int[500_000];
    static int size = 0;
    static int front = -1;
    static int rear = -1;

    public static void push(int x) {
        rear = (rear + 1) % 500_000;
        queue[rear] = x;
        size++;
    }

    public static int pop() {
        front = (front + 1) % 500_000;
        size--;
        return queue[front];
    }

    public static void main(String[] args) throws IOException {
        int num = readInt();
        for (int i = 1; i <= num; i++) push(i);

        while (size != 1) {
            pop();
            push(pop());
        }

        System.out.print(pop());
    }

    public static int readInt() throws IOException {
        cmd = 0;
        while((c = System.in.read()) > 47) cmd = (cmd << 3) + (cmd << 1) + (c & 15);
        return cmd;
    }
}
