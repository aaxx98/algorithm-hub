import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

  static int k, w, h;
  static int[][] map;
  static boolean[][][] visited; // 남은 말 동작 - k 개수별로 저장한다.
  static int[][] horse = {{1, 2}, {2, 1}, {-1, 2}, {-2, 1}, {1, -2}, {2, -1}, {-1, -2}, {-2, -1}};
  static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    k = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    w = Integer.parseInt(st.nextToken());
    h = Integer.parseInt(st.nextToken());
    map = new int[h][w];
    visited = new boolean[h][w][k + 1];

    for (int i = 0; i < h; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < w; j++) {
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int result = bfs(0, 0, 0);

    bw.write(result + " ");

    bw.flush();
    bw.close();
    br.close();
  }

  public static int bfs(int startY, int startX, int hm) {
    Deque<Monkey> q = new ArrayDeque<>();
    visited[startY][startX][hm] = true;
    q.offer(new Monkey(startY, startX, hm, 0));

    while (!q.isEmpty()) {
      Monkey cur = q.poll(); // 맨 앞 요소 꺼내기
      if (cur.x == w - 1 && cur.y == h - 1) {
        return cur.dist;
      }
      for (int d = 0; d < 4; d++) {
        int nx = cur.x + dir[d][1];
        int ny = cur.y + dir[d][0];

        if (nx < 0 || ny < 0 || nx >= w || ny >= h
            || visited[ny][nx][cur.k]
            || map[ny][nx] == 1) {
          continue;
        }

        visited[ny][nx][cur.k] = true;
        q.offer(new Monkey(ny, nx, cur.k, cur.dist + 1));
      }

      for (int d = 0; d < 8; d++) {
        if (cur.k < k) {
          int nx = cur.x + horse[d][1];
          int ny = cur.y + horse[d][0];

          if (nx < 0 || ny < 0 || nx >= w || ny >= h
              || visited[ny][nx][cur.k + 1]
              || map[ny][nx] == 1) {
            continue;
          }

          visited[ny][nx][cur.k + 1] = true;
          q.offer(new Monkey(ny, nx, cur.k + 1, cur.dist + 1));
        }
      }
    }
    return -1;
  }
}


class Monkey {

  int y, x, k;
  int dist;

  Monkey(int y, int x, int k, int dist) {
    this.y = y;
    this.x = x;
    this.k = k;
    this.dist = dist;
  }
}