import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

  static int[][][] map; // h, n, m
  static boolean[][][] visited;
  static int n, m, h, day = 0;

  // 평면 인접 방향
  static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
  // 상하 인접 방향
  static int[] h_dir = {1, -1};
  static List<Node> tomato = new ArrayList<>();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    StringTokenizer st = new StringTokenizer(br.readLine());

    m = Integer.parseInt(st.nextToken());
    n = Integer.parseInt(st.nextToken());
    h = Integer.parseInt(st.nextToken());

    map = new int[h][n][m];
    visited = new boolean[h][n][m];

    for (int i = 0; i < h; i++) {
      for (int y = 0; y < n; y++) {
        st = new StringTokenizer(br.readLine());
        for (int x = 0; x < m; x++) {
          map[i][y][x] = Integer.parseInt(st.nextToken());
          if (map[i][y][x] == 1) {
            tomato.add(new Node(i, y, x));
          }
        }
      }
    }

    bfs();

    if (findZero()) {
      bw.write("-1");
    } else {
      bw.write(day - 1 + " ");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  static boolean findZero() {
    for (int i = 0; i < h; i++) {
      for (int y = 0; y < n; y++) {
        for (int x = 0; x < m; x++) {
          if (map[i][y][x] == 0) {
            return true;
          }
        }
      }
    }
    return false;
  }

  public static void bfs() {
    Deque<Node> q = new ArrayDeque<>();

    for (Node n : tomato) {
      q.offer(n);
      visited[n.h][n.y][n.x] = true;
    }

    while (!q.isEmpty()) {
      int size = q.size();
      for (int i = 0; i < size; i++) {
        Node cur = q.poll();

        // 평면
        for (int d = 0; d < 4; d++) {
          int nx = cur.x + dir[d][1];
          int ny = cur.y + dir[d][0];

          if (nx < 0 || ny < 0 || nx >= m || ny >= n) {
            continue;
          }
          if (visited[cur.h][ny][nx]) {
            continue;
          }
          if (map[cur.h][ny][nx] == -1) {
            continue;
          }

          visited[cur.h][ny][nx] = true;
          map[cur.h][ny][nx] = 1;
          q.offer(new Node(cur.h, ny, nx));
        }
        // 위아래
        for (int d = 0; d < 2; d++) {
          int nh = cur.h + h_dir[d];

          if (nh < 0 || nh >= h) {
            continue;
          }
          if (visited[nh][cur.y][cur.x]) {
            continue;
          }
          if (map[nh][cur.y][cur.x] == -1) {
            continue;
          }

          visited[nh][cur.y][cur.x] = true;
          map[nh][cur.y][cur.x] = 1;
          q.offer(new Node(nh, cur.y, cur.x));
        }
      }
      day++;
    }
  }
}

class Node {

  int y, x, h;

  Node(int h, int y, int x) {
    this.h = h;
    this.y = y;
    this.x = x;
  }
}