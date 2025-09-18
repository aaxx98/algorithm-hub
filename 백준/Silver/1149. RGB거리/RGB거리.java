import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  static int R = 0, G = 1, B = 2;
  static int memo[][];

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    memo = new int[3][t + 1];

    for (int i = 1; i <= t; i++) {

      StringTokenizer st = new StringTokenizer(br.readLine());

      int r = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      memo[R][i] = Math.min(memo[G][i - 1] + r, memo[B][i - 1] + r);
      memo[G][i] = Math.min(memo[R][i - 1] + g, memo[B][i - 1] + g);
      memo[B][i] = Math.min(memo[G][i - 1] + b, memo[R][i - 1] + b);
    }

    int min = 1000000;

    min = Math.min(memo[R][t], memo[G][t]);
    min = Math.min(min, memo[B][t]);

    bw.write(min + " ");

    bw.flush();
    bw.close();
    br.close();
  }
}
