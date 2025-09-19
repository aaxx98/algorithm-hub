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

  static int EMPTY = 0, WALL = 1, VIRUS = 2;
  static int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
  static int n, m;

  static int[][] map, newMap;
  static boolean[][] visited;
  static List<Node> emptyList = new ArrayList<>();
  static List<Node> virusList = new ArrayList<>();
  static Node[] wallList = new Node[3];
  static int result = 0;


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
        if (map[i][j] == EMPTY) {
          emptyList.add(new Node(i, j));
        } else if (map[i][j] == VIRUS) {
          virusList.add(new Node(i, j));
        }
      }
    }

    pickWall(0, 0);

    bw.write(result + "\n");

    bw.flush();
    bw.close();
    br.close();
  }

  static void pickWall(int idx, int count) {
    if (count == 3) {
      // 새 맵에 벽 세우기
      newMap = new int[n][m];
      visited = new boolean[n][m];
      for (int i = 0; i < n; i++) {
        newMap[i] = map[i].clone();
      }
      for (int i = 0; i < 3; i++) {
        Node n = wallList[i];
        newMap[n.y][n.x] = WALL;
      }

      // 바이러스 확산
      for (Node n : virusList) {
        bfs(n.y, n.x);
      }

      // 안전구역 세기
      int cnt = 0;
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (newMap[i][j] == 0) {
            cnt++;
          }
        }
      }
      if (result < cnt) {
        result = cnt;
      }

      return;
    }
    for (int i = idx; i < emptyList.size(); i++) {
      wallList[count] = emptyList.get(i);
      pickWall(i + 1, count + 1);
    }
  }

  public static void bfs(int startY, int startX) {
    Deque<Node> q = new ArrayDeque<>();
    visited[startY][startX] = true;
    q.offer(new Node(startY, startX));

    while (!q.isEmpty()) {
      Node cur = q.poll();
      int y = cur.y;
      int x = cur.x;

      for (int d = 0; d < 4; d++) {
        int nx = x + dir[d][1];
        int ny = y + dir[d][0];

        if (nx < 0 || ny < 0 || nx >= m || ny >= n || visited[ny][nx] || newMap[ny][nx] != EMPTY) {
          continue;
        }

        visited[ny][nx] = true;
        newMap[ny][nx] = VIRUS;
        q.offer(new Node(ny, nx));
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