import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

  static int[] queens;
  static int n;
  static int cnt = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());
    queens = new int[n];

    nQueen(0);
    bw.write(cnt + " ");

    bw.flush();
    bw.close();
    br.close();
  }

  static void nQueen(int idx) {
    if (idx == n) {
      cnt++;
      return;
    }
    for (int i = 0; i < n; i++) {
      queens[idx] = i;
      if (check(idx)) {
        nQueen(idx + 1);
      }
    }
  }

  static boolean check(int idx) {
    for (int i = 0; i < idx; i++) {
      // 상하
      if (queens[i] == queens[idx]) {
        return false;
      }
      // 대각선
      else if (idx - i == Math.abs(queens[idx] - queens[i])) {
        return false;
      }
    }

    return true;
  }
}