import java.io.*;
import java.util.*;

public class BOJ1991 {
	static StringBuilder preSb = new StringBuilder();
	static StringBuilder inSb = new StringBuilder();
	static StringBuilder postSb = new StringBuilder();
	
	public static class Tree {
		HashMap<Character, Node> nodeMap;
		
		Tree() {
			this.nodeMap = new HashMap<>();
		}
		
		public void add(char item, Node left, Node right) {
			Node parent = null;
			if (nodeMap.containsKey(item)) {
				parent = nodeMap.get(item);
			} else {
				parent = new Node(item, null, null);
				nodeMap.put(item, parent);
			}
			
			if (left != null) {
				nodeMap.put(left.item, left);
				parent.left = left;
			}
			
			if (right != null) {
				nodeMap.put(right.item, right);
				parent.right = right;
			}
		}
		
		public void preorder(Node root) {
			if (root == null) return;
			preSb.append(root.item);
			preorder(root.left);
			preorder(root.right);
		}
		
		public void inorder(Node root) {
			if (root == null) return;
			inorder(root.left);
			inSb.append(root.item);
			inorder(root.right);
		}
		
		public void postorder(Node root) {
			if (root == null) return;
			postorder(root.left);
			postorder(root.right);
			postSb.append(root.item);
		}
	}
	
	public static class Node {
		char item;
		Node left, right;
		
		Node(char item, Node left, Node right) {
			this.item = item;
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		Tree tree = new Tree();
		
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			char c = st.nextToken().charAt(0);
			Node left = new Node(st.nextToken().charAt(0), null, null);
			Node right = new Node(st.nextToken().charAt(0), null, null);
			
			tree.add(c,
					left.item == '.' ? null : left,
					right.item == '.' ? null : right);
		}
		
		tree.preorder(tree.nodeMap.get('A'));
		tree.inorder(tree.nodeMap.get('A'));
		tree.postorder(tree.nodeMap.get('A'));
		
		System.out.println(preSb);
		System.out.println(inSb);
		System.out.println(postSb);
	}

}
