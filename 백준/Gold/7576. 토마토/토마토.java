
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

  static int tomato = 0;
  static int find_tomato = 0;

  static int yl, xl;
  static int[] dy = {-1, 1, 0, 0};
  static int[] dx = {0, 0, -1, 1};
  static Queue<Node> queue = new ArrayDeque<>();
  static List<Node> tomato_list = new ArrayList<>();
  static int[][] map;
  static int[][] visited;

  static int ans = 1;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    xl = Integer.parseInt(st.nextToken());
    yl = Integer.parseInt(st.nextToken());
    map = new int[yl][xl];
    visited = new int[yl][xl];

    for (int i = 0; i < yl; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < xl; j++) {
        int t = Integer.parseInt(st.nextToken());
        if (t != -1) {
          tomato++;
        }
        if (t == 1) {
          tomato_list.add(new Node(i, j));
          visited[i][j] = 1;
          find_tomato++;
        }
        map[i][j] = t;
      }
    }

    for (Node t : tomato_list) {
      queue.offer(t);
    }
    bfs();

    if (find_tomato != tomato) {
      System.out.println(-1);
    } else {
      System.out.println(ans - 1);
    }
//    System.out.println(Arrays.deepToString(visited));
//    System.out.println(tomato + " " + find_tomato);
  }

  public static void bfs() {
    while (!queue.isEmpty()) {
      Node node = queue.poll();
      for (int d = 0; d < 4; d++) {
        int ny = node.y + dy[d];
        int nx = node.x + dx[d];
        if (ny < 0 || ny >= yl || nx < 0 || nx >= xl || visited[ny][nx] > 0 || map[ny][nx] == -1) {
          continue;
        }

        queue.offer(new Node(ny, nx));
        visited[ny][nx] = visited[node.y][node.x] + 1;
        find_tomato++;
        if (ans < visited[ny][nx]) {
          ans = visited[ny][nx];
        }
      }
    }
  }
}

class Node {

  int y, x;

  Node(int y, int x) {
    this.y = y;
    this.x = x;
  }
}