
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  static int[][] memoi;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());

    memoi = new int[N][N];
    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        int value = Integer.parseInt(st.nextToken());
        if (i - 1 >= 0) {
          value += memoi[i - 1][j];
        }
        if (j - 1 >= 0) {
          value += memoi[i][j - 1];
        }
        if (i - 1 >= 0 && j - 1 >= 0) {
          value -= memoi[i - 1][j - 1];
        }
        memoi[i][j] = value;
      }
    }

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int x1 = Integer.parseInt(st.nextToken());
      int x2 = Integer.parseInt(st.nextToken());
      int y1 = Integer.parseInt(st.nextToken());
      int y2 = Integer.parseInt(st.nextToken());
      bw.write(calc(x1 - 1, x2 - 1, y1 - 1, y2 - 1) + "\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }


  public static int calc(int x1, int x2, int y1, int y2) {
    int result = memoi[y1][y2];
    if (x1 - 1 >= 0) {
      result -= memoi[x1 - 1][y2];
    }
    if (x2 - 1 >= 0) {
      result -= memoi[y1][x2 - 1];
    }
    if (x1 - 1 >= 0 && x2 - 1 >= 0) {
      result += memoi[x1 - 1][x2 - 1];
    }
    return result;
  }
}
