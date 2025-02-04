import java.io.*;

public class BOJ5052 {
    public static void main(String[] args) throws Exception {
        InputReader in = new InputReader(System.in);
        StringBuilder sb = new StringBuilder();
        int T = in.readInt();

        while (T-- > 0) {
            int n = in.readInt();
            Trie trie = new Trie();
            String[] numbers = new String[n];
            boolean consistent = true;

            for (int i = 0; i < n; i++) {
                numbers[i] = in.readString();
            }

            for (String number : numbers) {
                if (!trie.insert(number)) {
                    consistent = false;
                    break;
                }
            }

            sb.append(consistent ? "YES\n" : "NO\n");
        }

        System.out.print(sb);
    }

    static class Trie {
        TrieNode root;

        Trie() {
            root = new TrieNode();
        }

        boolean insert(String key) {
            TrieNode node = root;
            for (int i = 0; i < key.length(); i++) {
                int idx = key.charAt(i) - '0';

                if (node.children[idx] == null) {
                    node.children[idx] = new TrieNode();
                }

                node = node.children[idx];

                if (node.isEnd) return false;
            }

            node.isEnd = true;

            for (int i = 0; i < 10; i++) {
                if (node.children[i] != null) return false;
            }

            return true;
        }
    }

    static class TrieNode {
        TrieNode[] children = new TrieNode[10];
        boolean isEnd = false;
    }

    static class InputReader {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int cur, size;

        public InputReader(InputStream in) {
            this.in = in;
        }

        private int next() throws IOException {
            if (cur >= size) {
                cur = 0;
                size = in.read(buffer);
                if (size == -1) return -1;
            }
            return buffer[cur++];
        }

        public int readInt() throws IOException {
            int num = 0, c;
            boolean neg = false;

            while ((c = next()) <= ' ') ;
            if (c == '-') {
                neg = true;
                c = next();
            }
            do {
                num = num * 10 + (c - '0');
            } while ((c = next()) >= '0' && c <= '9');

            return neg ? -num : num;
        }

        public String readString() throws IOException {
            StringBuilder sb = new StringBuilder();
            int c;
            while ((c = next()) <= ' ') ;
            do {
                sb.append((char) c);
            } while ((c = next()) > ' ');
            return sb.toString();
        }
    }
}