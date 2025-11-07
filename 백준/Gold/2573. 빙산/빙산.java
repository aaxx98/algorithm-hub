import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int[][] map;
  static boolean[][] visited;
  static List<Point> dfsPoint = new ArrayList<>();
  static int n, m;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    map = new int[n][m];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
        if (map[i][j] != 0) {
          dfsPoint.add(new Point(i, j));
        }
      }
    }

    int year = 0;
    while (true) {
      int count = 0;
      visited = new boolean[n][m];
      for (Point p : dfsPoint) {
        if (map[p.x][p.y] > 0 && !visited[p.x][p.y]) {
          dfs(p.x, p.y);
          count++;
        }
      }
      if (count == 0) {
        year = 0;
        break;
      }
      if (count > 1) {
        break;
      }
      year++;
      after1Year();
    }

    bw.write(year + " ");

    bw.flush();
    bw.close();
    br.close();
  }

  static void after1Year() {
    int[][] newMap = new int[n][m];
    dfsPoint.clear();
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == 0) {
          continue;
        }
        Point p = new Point(i, j);
        newMap[i][j] = map[i][j] - p.getAdjacentCount(n, m, map);

        if (newMap[i][j] > 0) {
          dfsPoint.add(p);
        } else {
          newMap[i][j] = 0;
        }
      }
    }
    map = newMap;
  }

  static void dfs(int x, int y) {
    if (x < 0 || y < 0 || x >= n || y >= m || visited[x][y] || map[x][y] == 0) {
      return;
    }
    visited[x][y] = true;

    dfs(x - 1, y);
    dfs(x + 1, y);
    dfs(x, y - 1);
    dfs(x, y + 1);
  }
}

class Point {

  int x, y;

  Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean isValid(int n, int m) {
    if (x >= 0 && x < n && y >= 0 && y < m) {
      return true;
    }
    return false;
  }

  public int getAdjacentCount(int n, int m, int[][] map) {
    List<Point> points = new ArrayList<>(
        Arrays.asList(
            new Point(this.x, this.y - 1),
            new Point(this.x, this.y + 1),
            new Point(this.x - 1, this.y),
            new Point(this.x + 1, this.y)
        )
    );
    int res = 0;
    for (Point l : points) {
      if (l.isValid(n, m) && map[l.x][l.y] == 0) {
        res++;
      }
    }
    return res;
  }
}