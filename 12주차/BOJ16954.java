import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static Stack<Integer> stack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[8][8];
        stack = new Stack<>();
        for(int i = 0; i < 8; i++){
            String s = br.readLine();
            if(!s.equals("........")) stack.add(i);

            for(int j = 0; j < 8; j++){
                map[i][j] = s.charAt(j);
            }
        }

        System.out.print(gogo());
    }

    public static int gogo(){
        int curY = 7;
        int curX = 0;

        while(!stack.isEmpty()){
            int nextWallArea = stack.pop();
            boolean escaped = false;
            for(int i = 7; i >= 0; i--){
                char cur = map[nextWallArea][i];
                if(cur == '.'){
                    int xMove = Math.abs(i - curX);
                    int yMove = curY - nextWallArea;
                    if(xMove <= yMove){
                        if(!(nextWallArea - 1 >=0 && nextWallArea + 1 < 8 && (map[nextWallArea - 1][i] == '#' && map[nextWallArea + 1][i] == '#'))) {
                            curY = nextWallArea;
                            curX = i;
                            escaped = true;
                            break;
                        }
                    }
                }
            }

            if(!escaped) return 0;
        }

        return 1;
    }

}
