import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  static String[] map;
  static boolean[][] visited;
  static int x, y, cnt;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    y = Integer.parseInt(st.nextToken());
    x = Integer.parseInt(st.nextToken());
    cnt = 0;
    map = new String[y];
    visited = new boolean[y][x];

    for (int i = 0; i < y; i++) {
      map[i] = br.readLine();
    }
    for (int i = 0; i < y; i++) {
      if (backtrack(i, 0)) {
        cnt++;
      }
    }

    bw.write(cnt + " ");

    bw.flush();
    bw.close();
    br.close();
  }

  public static boolean backtrack(int r, int c) {
    if (r < 0 || c < 0 || r >= y || c >= x) {
      return false;
    }
    if (visited[r][c] || map[r].charAt(c) == 'x') {
      return false;
    }
    visited[r][c] = true;
    if (c == x - 1) {
      return true;
    }
    for (int dy = -1; dy <= 1; dy++) {
      if (backtrack(r + dy, c + 1)) {
        return true;
      }
    }
    return false;
  }
}
