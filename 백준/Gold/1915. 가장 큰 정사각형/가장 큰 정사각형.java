import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  static int[][] map;
  static int maxValue = 0;
  static int n, m;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    map = new int[n][m];

    for (int i = 0; i < n; i++) {
      String line = br.readLine();
      if (maxValue == 0 && line.contains("1")) {
        maxValue = 1;
      }
      for (int j = 0; j < m; j++) {
        map[i][j] = line.charAt(j) - '0';

        if (map[i][j] == 1 && i - 1 >= 0 && j - 1 >= 0) {
          int min = Math.min(map[i - 1][j], map[i][j - 1]);
          min = Math.min(min, map[i - 1][j - 1]);
          map[i][j] = min + 1;
          maxValue = Math.max(maxValue, map[i][j]);
        }
      }
    }

    bw.write(maxValue * maxValue + " ");

    bw.flush();
    bw.close();
    br.close();
  }
}