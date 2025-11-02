import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  static int N;
  static long B;
  static long[][] mat;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    N = Integer.parseInt(st.nextToken());
    B = Long.parseLong(st.nextToken());

    mat = new long[N][N];

    for (int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < N; j++) {
        mat[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    long[][] result = pow(mat, B);
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        bw.write(result[i][j] % 1000 + " ");
      }
      bw.write("\n");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  public static long[][] multiple(long[][] a, long[][] b) {
    long[][] result = new long[N][N];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        result[i][j] = 0;
        for (int k = 0; k < N; k++) {
          result[i][j] += a[i][k] * b[k][j];
          result[i][j] = result[i][j] % 1000;
        }
      }
    }
    return result;
  }

  public static long[][] pow(long[][] m, long exp) {
    if (exp == 1) {
      return m;
    }
    long[][] result;
    long[][] half = pow(m, exp / 2);

    if (exp % 2 == 0) {
      result = multiple(half, half);
    } else {
      result = multiple(half, half);
      result = multiple(result, m);
    }
    return result;
  }
}
