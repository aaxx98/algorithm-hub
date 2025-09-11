
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class Main {

  static int[] dy = {-1, 1, 0, 0};
  static int[] dx = {0, 0, -1, 1};
  static char[][] map;
  static boolean[][] visit;

  static List<Integer> list = new ArrayList<>();
  static Queue<Node> queue = new ArrayDeque<>(); // bfs 큐

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());
    map = new char[n][n];
    visit = new boolean[n][n];
    for (int i = 0; i < map.length; i++) {
      map[i] = br.readLine().toCharArray();
    }

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        if (map[i][j] == '1' && !visit[i][j]) {
          list.add(bfs(i, j));
        }
      }
    }

    Collections.sort(list);
    System.out.println(list.size());
    for (int a : list) {
      System.out.println(a);
    }
  }

  static int bfs(int y, int x) {
    queue.offer(new Node(y, x));
    visit[y][x] = true;
    int cnt = 1;

    while (!queue.isEmpty()) {

      Node node = queue.poll();

      for (int d = 0; d < 4; d++) {
        int ny = node.y + dy[d];
        int nx = node.x + dx[d];

        if (ny < 0 || nx < 0 || ny >= map.length || nx >= map.length || map[ny][nx] != '1'
            || visit[ny][nx]) {
          continue;
        }

        queue.offer(new Node(ny, nx));
        visit[ny][nx] = true;
        cnt++;
      }
    }
    return cnt;
  }

  static class Node {

    int y, x;

    Node(int y, int x) {
      this.y = y;
      this.x = x;
    }
  }
}
