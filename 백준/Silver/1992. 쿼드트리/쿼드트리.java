import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

  static String[] map;

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N = Integer.parseInt(br.readLine());
    map = new String[N];

    for (int i = 0; i < N; i++) {
      map[i] = br.readLine();
    }

    bw.write(quad(new Point(0, 0), N));

    bw.flush();
    bw.close();
    br.close();
  }

  public static String quad(Point p, int q) { // (x,y)가 시작점이고 크기 8인 맵을 탐색
    if (q == 1) {
      return p.toString();
    }
    int half = q / 2;
    Point p1 = new Point(p.x, p.y);
    Point p2 = new Point(p.x, p.y + half);
    Point p3 = new Point(p.x + half, p.y);
    Point p4 = new Point(p.x + half, p.y + half);

    String q1 = quad(p1, half);
    String q2 = quad(p2, half);
    String q3 = quad(p3, half);
    String q4 = quad(p4, half);
    if (q1.length() == 1 && q2.equals(q1) && q3.equals(q1) && q4.equals(q1)) {
      return p1.toString();
    }
    return "(" + q1 + q2 + q3 + q4 + ")";
  }


  static class Point {

    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }

    @Override
    public String toString() { // 출력 0 or 1
      return String.valueOf(map[x].charAt(y));
    }

    @Override
    public boolean equals(Object obj) { // 0, 1 비교
      if (this == obj) {
        return true;
      }
      if (obj == null || getClass() != obj.getClass()) {
        return false;
      }
      Point p = (Point) obj;
      return map[x].charAt(y) == map[p.x].charAt(p.y);
    }
  }
}
