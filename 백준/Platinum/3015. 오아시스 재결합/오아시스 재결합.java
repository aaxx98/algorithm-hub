import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int t = Integer.parseInt(br.readLine());
    long result = 0;

    ArrayDeque<People> showPeople = new ArrayDeque<>();
    for (int i = 0; i < t; i++) {
      int h = Integer.parseInt(br.readLine());
      int cnt = 1;

      while (!showPeople.isEmpty() && showPeople.peek().height < h) {
        result += showPeople.pop().count;
      }
      if (!showPeople.isEmpty() && showPeople.peek().height == h) {
        People same = showPeople.pop();
        result += same.count;
        cnt = same.count + 1;

        if (!showPeople.isEmpty()) {
          result += 1;
        }
      } else {
        if (!showPeople.isEmpty()) {
          result += 1;
        }
      }

      People p = new People(h, cnt);
      showPeople.push(p);
    }

    bw.write(result + "");

    bw.flush();
    bw.close();
    br.close();
  }
}

class People {

  int count;
  int height;

  People(int height, int count) {
    this.height = height;
    this.count = count;
  }
}