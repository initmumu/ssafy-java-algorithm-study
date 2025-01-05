import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder first = new StringBuilder();
    static StringBuilder middle = new StringBuilder();
    static StringBuilder last = new StringBuilder();

    static Node[] tree;

    public static class Node{
        public char c;
        public char left;
        public char right;

        public Node(char c, char left, char right){
            this.c = c;
            this.left = left;
            this.right = right;
        }
    }

    public static void DFS(char c){
        first.append(c);

        if(tree[c - 'A'].left != '.') DFS(tree[c - 'A'].left);
        middle.append(c);
        if(tree[c - 'A'].right != '.') DFS(tree[c - 'A'].right);

        last.append(c);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        tree = new Node[N];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());

            char cur = st.nextToken().charAt(0);
            char left = st.nextToken().charAt(0);
            char right = st.nextToken().charAt(0);

            tree[cur - 'A'] = new Node(cur, left, right);
        }

        DFS('A');

        System.out.println(first);
        System.out.println(middle);
        System.out.println(last);
    }
}
