import java.util.ArrayList;
import java.util.List;

class Solution {

  static char map[][];
  static boolean visited[][];
  static int n, m;
  static List<Point> outPoints = new ArrayList<>();

  public int solution(String[] storage, String[] requests) {
    n = storage.length;
    m = storage[0].length();
    map = new char[n][m];
    int cm = 0;
    for (String s : storage) {
      map[cm++] = s.toCharArray();
    }

    for (String r : requests) {
      if (r.length() > 1) {
        pickAll(r.charAt(0));
      } else {
        pickOut(r.charAt(0));
      }
    }

    int ans = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] >= 'A' && map[i][j] <= 'Z') {
          ans++;
        }
      }
    }

    return ans;
  }

  void dfs(int x, int y, char c) {
    Point p = new Point(x, y);
    if (!p.isValid(n, m) || visited[x][y]) {
      return;
    }
    if (map[x][y] >= 'A' && map[x][y] <= 'Z') {
      if (map[x][y] == c) {
        outPoints.add(p);
      }
      return;
    }
    visited[x][y] = true;

    // 상하좌우 재귀
    dfs(x - 1, y, c);
    dfs(x + 1, y, c);
    dfs(x, y - 1, c);
    dfs(x, y + 1, c);
  }

  public void pickAll(char c) {
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        if (map[i][j] == c) {
          map[i][j] = '.';
        }
      }
    }
  }

  public void pickOut(char c) {
    visited = new boolean[n][m];
    for (int i = 0; i < n; i++) {
      dfs(i, 0, c);
      dfs(i, m - 1, c);
    }
    for (int j = 0; j < m; j++) {
      dfs(0, j, c);
      dfs(n - 1, j, c);
    }

    for (Point o : outPoints) {
      map[o.x][o.y] = '.';
    }
    outPoints.clear();
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
}
