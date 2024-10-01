import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String plainText = br.readLine();
        String cipherText = br.readLine();

        StringBuilder key = new StringBuilder();

        for (int i = 0; i < plainText.length(); i++) {
            int plainChar = plainText.charAt(i) - 'A';
            int cipherChar = cipherText.charAt(i) - 'A';
            int keyChar = (cipherChar - plainChar + 25) % 26;
            key.append((char) (keyChar + 'A'));
        }

        System.out.println(getShortestKey(key.toString()));
    }

    static String getShortestKey(String key) {
        int len = key.length();

        for (int i = 1; i <= len; i++) {
            if (len % i == 0) {
                String subKey = key.substring(0, i);
                boolean isValid = true;

                for (int j = 0; j < len; j++) {
                    if (key.charAt(j) != subKey.charAt(j % i)) {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    return subKey;
                }
            }
        }

        return key;
    }
}
