import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.stream.Stream;

public class Main {
    static int T;
    static int N;
    static BufferedReader br;

    public static class Node {
        public int value;
        public HashMap<Integer, Node> children;

        public Node(){
            children = new HashMap<>();
        }
        public Node(int value) {
            this.value = value;
            children = new HashMap<>();
        }

        public Node getChild(int i){
            if(children == null) return null;
            if(children.containsKey(i)){
                return children.get(i);
            }
            return null;
        }
    }

    public static class Trie {
        public Node root;
        public Trie() {
            root = new Node();
        }
    }

    public static boolean run() throws IOException{
        N = Integer.parseInt(br.readLine());
        Trie trie = new Trie();

        String[] lines = new String[N];
        for(int i = 0; i < N; i++){
            lines[i] = br.readLine();
        }

        for (int i = 0; i < N; i++) {
            int[] tokens = Stream.of(lines[i].split("")).mapToInt(Integer::parseInt).toArray();
            Node cur = trie.root;
            boolean same = true;
            for (int token : tokens) {
                Node child = cur.getChild(token);

                if (child != null) {
                    if(child.children.isEmpty()){
                        return true;
                    }
                    cur = child;
                } else {
                    same = false;
                    Node newChild = new Node(token);
                    cur.children.put(token, newChild);
                    cur = newChild;
                }
            }
            if(same) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            sb.append(run() ? "NO": "YES").append('\n');
        }
        System.out.print(sb);
    }
}
