import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

  static int[] memo;
  static int n;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    n = Integer.parseInt(br.readLine());
    memo = new int[n + 1];

    for (int i = 3; i <= n; i++) {
      if (i % 3 == 0) {
        memo[i] = i / 3;
      }
      if (i % 5 == 0) {
        memo[i] = i / 5;
      }
      if (memo[i - 3] > 0) {
        memo[i] = memo[i - 3] + 1;
      }
      if (i - 5 > 0 && memo[i - 5] > 0) {
        if (memo[i] > 0) {
          memo[i] = Math.min(memo[i], memo[i - 5] + 1);
        }
      }
    }
    if (memo[n] == 0) {
      System.out.println(-1);
    } else {
      System.out.println(memo[n]);
    }
  }
}
