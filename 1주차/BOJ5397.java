import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ5397 {
    public static class Node{
        public Node left;
        public Node right;
        public char c;

        Node(char c){
            this.c = c;
        }
    }

    public static class LinkedList{
        Node head, tail;

        LinkedList(){
            head = new Node('/');
            tail = head;
        }

        public void Add(Node cur, Node newNode) {
            if(cur == head) head = newNode;
            else cur.left.right = newNode;

            newNode.left = cur.left;
            newNode.right = cur;

            cur.left = newNode;
        }

        public void Remove(Node node) {
            if(node == head) return;

            if(node.left == head) head = node;
            else node.left.left.right = node;
            node.left = node.left.left;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        for(int t = 0; t < T; t++) {
            LinkedList text = new LinkedList();

            Node cursor = text.head;

            char[] instructionSet = br.readLine().toCharArray();

            for(char instruction : instructionSet) {
                switch(instruction){
                    case '<':
                        if(cursor.left != null) cursor = cursor.left;
                        break;

                    case '>':
                        if(cursor.right != null) cursor = cursor.right;
                        break;

                    case '-':
                        text.Remove(cursor);
                        break;

                    default:
                        text.Add(cursor, new Node(instruction));
                        break;
                }
            }

            Node temp = text.head;
            StringBuilder sb = new StringBuilder();
            while(temp != text.tail) {
                sb.append(temp.c);
                temp = temp.right;
            }
            System.out.println(sb);
        }
        br.close();
    }
}
