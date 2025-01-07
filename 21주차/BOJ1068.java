import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Node[] nodes;

    public static class Node {
        public int parent;
        public HashSet<Integer> children;
        public boolean deleted;
        public Node(){
            deleted = false;
            children = new HashSet<>();
        }

        public void addChild(int child){
            children.add(child);
        }

        public void deleteNode() {
            deleted = true;
            for(int child : children) {
                nodes[child].deleteNode();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        nodes = new Node[N];

        for(int i = 0; i < N; i++){
            nodes[i] = new Node();
        }

        for(int i = 0; i < N; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1){
                continue;
            }
            nodes[i].parent = parent;
            nodes[parent].addChild(i);
        }

        int target = Integer.parseInt(br.readLine());

        Node targetNode = nodes[target];
        targetNode.deleteNode();

        nodes[targetNode.parent].children.remove(target);

        int count = 0;
        for(int i = 0; i < N; i++){
            if(!nodes[i].deleted && nodes[i].children.isEmpty()){
                count++;
            }
        }

        System.out.print(count);
    }
}