
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

  static BufferedWriter bw;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());

    int total = (int) Math.pow(2, t) - 1;
    bw.write(total + "\n");

    hanoi(t, 1, 2, 3);
    bw.flush();
    bw.close();
    br.close();
  }

  public static void hanoi(int n, int from, int via, int to) throws IOException {

    if (n == 1) {
      bw.write(from + " " + to + "\n");
      return;
    }
    hanoi(n - 1, from, to, via);
    bw.write(from + " " + to + "\n");
    hanoi(n - 1, via, from, to);
  }
}
