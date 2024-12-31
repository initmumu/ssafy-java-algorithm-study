import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class BOJ1991 {

    static Map<Character, Character[]> tree = new HashMap<>();
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            char node = input.charAt(0);
            char left = input.charAt(2);
            char right = input.charAt(4);
            tree.put(node, new Character[]{left, right});
        }

        preorderTraverse('A');
        result.append("\n");
        inorderTraverse('A');
        result.append("\n");
        postorderTraverse('A');

        System.out.println(result);
    }
    
    // 전위 순회
    static void preorderTraverse(char node) {
        if (node == '.') {
            return;
        }
        result.append(node);
        preorderTraverse(tree.get(node)[0]);
        preorderTraverse(tree.get(node)[1]);
    }
    
    // 중위 순회
    static void inorderTraverse(char node) {
        if (node == '.') {
            return;
        }
        inorderTraverse(tree.get(node)[0]);
        result.append(node);
        inorderTraverse(tree.get(node)[1]);
    }

    // 후위 순회
    static void postorderTraverse(char node) {
        if (node == '.') {
            return;
        }
        postorderTraverse(tree.get(node)[0]);
        postorderTraverse(tree.get(node)[1]);
        result.append(node);
    }
}
