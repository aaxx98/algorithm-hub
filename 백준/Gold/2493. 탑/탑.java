import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());

    ArrayDeque<Pair> showTop = new ArrayDeque<>();
    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 0; i < t; i++) {
      int top = Integer.parseInt(st.nextToken());
      Pair p = new Pair(i + 1, top);
      if (showTop.isEmpty()) {
        bw.write(0 + " ");
      } else {
        while (!showTop.isEmpty() && p.val > showTop.peek().val) {
          showTop.pop();
        }
        if (showTop.isEmpty()) {
          bw.write(0 + " ");
        } else {
          bw.write(showTop.peek().index + " ");
        }
      }
      showTop.push(p);
    }

    bw.flush();
    bw.close();
    br.close();
  }
}

class Pair {

  int index;
  int val;

  Pair(int index, int val) {
    this.index = index;
    this.val = val;
  }
}