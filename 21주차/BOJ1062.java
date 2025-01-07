import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static Node[] nodes;
    static int max;

    public static class Node{
        public int data;

        public Node(String s){
            data = 0;
            for(char c : s.toCharArray()){
                data = data | (1 << (c - 'a'));
            }
        }
    }

    public static void DFS(int depth, int n, int data){
        if(depth == K){
            int count = 0;
            for(int i = 0; i < N; i++){
                Node newNode = nodes[i];
                if((newNode.data & data) == newNode.data){
                    count++;
                }
            }
            max = Math.max(max, count);
            return;
        }

        for(int i = n; i < 26; i++){
            if((data & (1 << i)) != 0) continue;
            DFS(depth + 1, i + 1, data | (1 << i));
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if(K < 5){
            System.out.print(0);
            return;
        }

        nodes = new Node[N];
        max = 0;

        for (int i = 0; i < N; i++) {
            nodes[i] = new Node(br.readLine());
        }

        int data = 0;
        data = data | (1 << ('a' - 'a'));
        data = data | (1 << ('n' - 'a'));
        data = data | (1 << ('t' - 'a'));
        data = data | (1 << ('i' - 'a'));
        data = data | (1 << ('c' - 'a'));

        DFS(5, 0, data);

        System.out.print(max);
    }
}
