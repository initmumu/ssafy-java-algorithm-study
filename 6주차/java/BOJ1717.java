import java.io.*;
import java.util.*;


public class Main {
    static int[] parent;

    public static int find(int n) {
        if (n != parent[n]) {
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }

    public static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if(x == y) return false;

        if(x <= y) parent[y] = x;
        else parent[x] = y;
        return true;
    }

    public static void main(String[] args) throws IOException {
        int n = readInt();
        int m = readInt();

        StringBuilder sb = new StringBuilder();
        parent = new int[n+1];

        for (int i = 0; i < n+1; i++) {
            parent[i] = i;
        }

        for (int c = 0; c < m; c++) {
            int inst = readInt();
            int a = readInt();
            int b = readInt();

            switch (inst) {
                case 0:
                    union(a, b);
                    break;
                case 1:
                    boolean r = find(a) == find(b);

                    if (r) System.out.println("YES");
                    else System.out.println("NO");
            }
        }


    }

    public static int readInt() throws IOException{
        int c, t = 0;
        while((c=System.in.read()) > 32) t = (t<<3) + (t<<1) + (c&15);
        if(c == 13) System.in.read();
        return t;
    }
}