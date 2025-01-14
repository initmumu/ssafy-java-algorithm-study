import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Node[] tree;
    static int[] newTree;
    static int max;

    static class Node{
        public int parent;
        public int weight;
        public int childCount;

        public Node(){
            this.childCount = 0;
        }

        public void set(int parent, int weight){
            this.parent = parent;
            this.weight = weight;
        }

        public boolean ifLeaf(){
            return childCount == 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        tree = new Node[N + 1];
        for(int i = 1; i <= N; i++){
            tree[i] = new Node();
        }

        max = 0;

        for(int i = 0; i < N - 1; i++){
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[child].set(parent, weight);
            tree[parent].childCount++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        newTree = new int[N + 1];
        for(int i = 1; i <= N; i++){
            if(tree[i].ifLeaf()){
                q.add(i);
            }
        }

        while(!q.isEmpty()){
            int qSize = q.size();

            for(int i = 0; i < qSize; i++){
                int child = q.poll();
                int parent = tree[child].parent;
                if(parent == 0) continue;

                int curChildWeight = newTree[child] + tree[child].weight;
                if(newTree[parent] == 0){
                    newTree[parent] = curChildWeight;
                    max = Math.max(max, curChildWeight);
                } else {
                    max = Math.max(max, newTree[parent] + curChildWeight);
                    newTree[parent] = Math.max(curChildWeight, newTree[parent]);
                }

                tree[parent].childCount--;
                if(tree[parent].childCount != 0) continue;
                q.add(parent);
            }
        }

        System.out.println(max);
    }
}
