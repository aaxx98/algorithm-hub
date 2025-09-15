import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  static StringTokenizer st;
  static int n, m, result;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());

    for (int i = 0; i < t; i++) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());
      result = 0;
      System.out.println(comb(m, n));
    }
  }

  static int comb(int m, int n) {
    int[][] memo = new int[m + 1][m + 1];

    memo[1][0] = 1;
    memo[1][1] = 1;
    for (int i = 2; i <= m; i++) {
      memo[i][0] = 1;
      memo[i][i] = 1;
    }
    for (int i = 2; i <= m; i++) {
      for (int j = 1; j < i; j++) {
        memo[i][j] = memo[i - 1][j - 1] + memo[i - 1][j];
      }
    }

    return memo[m][n];
  }
}