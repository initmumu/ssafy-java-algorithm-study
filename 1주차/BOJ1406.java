import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ1406 {
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

		public void AddLeft(Node cur, Node newNode) {
			if(cur == head) head = newNode;
			else cur.left.right = newNode;

			newNode.left = cur.left;
			newNode.right = cur;

			cur.left = newNode;
		}

		public void AddRight(Node newNode) {
			if(head == null) {
				head = newNode;
				tail = newNode;
				return;
			}

			tail.right = newNode;
			newNode.left = tail;
			tail = newNode;
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
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		char[] str = br.readLine().toCharArray();
		int M = Integer.parseInt(br.readLine());

		LinkedList text = new LinkedList();
		Node cursor;

		for(char c : str) {
			text.AddRight(new Node(c));
		}

		text.AddRight(new Node('/'));

		cursor = text.tail;


		for(int i = 0; i < M; i++) {
			String[] instruction = br.readLine().split(" ");
			switch(instruction[0]){
				case "L":
					if(cursor.left != null) cursor = cursor.left;
					break;

				case "D":
					if(cursor.right != null) cursor = cursor.right;
					break;

				case "B":
					text.Remove(cursor);
					break;

				case "P":
					text.AddLeft(cursor, new Node(instruction[1].charAt(0)));
					break;
			}
		}

		Node temp = text.head;
		while(temp != text.tail) {
			bw.write(temp.c);
			temp = temp.right;
		}
		bw.flush();
		br.close();
		bw.close();
	}
}