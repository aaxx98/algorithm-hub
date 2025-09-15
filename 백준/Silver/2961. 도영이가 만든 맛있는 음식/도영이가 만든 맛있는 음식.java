import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static StringTokenizer st;
  static int t;
  static long MAX = 100000000;

  static int[] s, b;
  static long result;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    t = Integer.parseInt(br.readLine());
    s = new int[t];
    b = new int[t];

    for (int i = 0; i < t; i++) {
      st = new StringTokenizer(br.readLine());
      s[i] = Integer.parseInt(st.nextToken());
      b[i] = Integer.parseInt(st.nextToken());
    }
    result = MAX;
    dfs(0, 1, 0);

    System.out.println(result);
  }

  static void dfs(int depth, long total_s, long total_b) {
    if (depth == t) {
      if (total_b == 0) {
        return;
      }
      if (Math.abs(total_s - total_b) < result) {
        result = Math.abs(total_s - total_b);
      }
      return;
    }

    dfs(depth + 1, total_s * s[depth], total_b + b[depth]);
    dfs(depth + 1, total_s, total_b);
  }
}
