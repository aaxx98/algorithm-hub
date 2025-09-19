import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());

    for (int tc = 0; tc < t; tc++) {
      int n = Integer.parseInt(br.readLine());
      int[][] sticker = new int[2][n];
      int[][] dp = new int[2][n];

      StringTokenizer st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        sticker[0][i] = Integer.parseInt(st.nextToken());
      }
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        sticker[1][i] = Integer.parseInt(st.nextToken());
      }

      dp[0][0] = sticker[0][0];
      dp[1][0] = sticker[1][0];

      if (n > 1) {
        dp[0][1] = sticker[0][1] + dp[1][0];
        dp[1][1] = sticker[1][1] + dp[0][0];
      }

      for (int i = 2; i < n; i++) {
        dp[0][i] = sticker[0][i] + Math.max(dp[1][i - 1], dp[1][i - 2]);
        dp[1][i] = sticker[1][i] + Math.max(dp[0][i - 1], dp[0][i - 2]);
      }
      bw.write(Math.max(dp[0][n - 1], dp[1][n - 1]) + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}