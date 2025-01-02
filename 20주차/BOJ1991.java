import java.io.*;
import java.util.*;

public class BOJ1991 {

	static class Node {
		char parent;
		Node left;
		Node right;

		Node(char parent) {
			this.parent = parent;
		}
	}

	static Map<Character, Node> tree = new HashMap<>();
	static StringBuilder preorder = new StringBuilder();
	static StringBuilder inorder = new StringBuilder();
	static StringBuilder postorder = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char parent = st.nextToken().charAt(0);
			char left = st.nextToken().charAt(0);
			char right = st.nextToken().charAt(0);

			tree.putIfAbsent(parent, new Node(parent));

			if (left != '.') {
				tree.putIfAbsent(left, new Node(left));
				tree.get(parent).left = tree.get(left);
			}

			if (right != '.') {
				tree.putIfAbsent(right, new Node(right));
				tree.get(parent).right = tree.get(right);
			}
		}

		dfs(tree.get('A'));

		System.out.println(preorder.toString());
		System.out.println(inorder.toString());
		System.out.println(postorder.toString());
	}

	private static void dfs(Node node) {
		if (node == null) return;

		preorder.append(node.parent);

		dfs(node.left);

		inorder.append(node.parent);

		dfs(node.right);

		postorder.append(node.parent);
	}
}
