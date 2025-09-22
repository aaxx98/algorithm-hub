import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

  static boolean[] selected = new boolean[11]; // 노드 선택 여부
  static boolean[] visited = new boolean[11]; // 방문 여부
  static int[] node = new int[11]; // 인구수
  static boolean[][] map = new boolean[11][11]; // 인접 노드 저장
  static int n, result = 1001, MAX_INT = 1001;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    n = Integer.parseInt(br.readLine());

    StringTokenizer st = new StringTokenizer(br.readLine());
    for (int i = 1; i <= n; i++) {
      node[i] = Integer.parseInt(st.nextToken());
    }

    // 인접 노드 갱신
    for (int i = 1; i <= n; i++) {
      st = new StringTokenizer(br.readLine());
      int num = Integer.parseInt(st.nextToken());
      for (int j = 0; j < num; j++) {
        int ad = Integer.parseInt(st.nextToken());
        map[i][ad] = true;
        map[ad][i] = true;
      }
    }

    makeCase(1);

    if (result == MAX_INT) {
      bw.write("-1");
    } else {
      bw.write(result + " ");
    }

    bw.flush();
    bw.close();
    br.close();
  }

  static int bfs(boolean value, int start) { // value = true or false
    int people = 0; // bfs 하면서 세기
    Deque<Integer> q = new ArrayDeque<>();
    visited[start] = true;
    q.offer(start);

    while (!q.isEmpty()) {
      int now = q.poll();

      people += node[now];
      for (int i = 1; i <= n; i++) {
        if (!map[now][i] || value != selected[i] || visited[i]) {
          continue;
        }
        visited[i] = true;
        q.offer(i);
      }
    }

    return people;
  }


  static void makeCase(int idx) {
    if (n < idx) {
      //  System.out.println(Arrays.toString(selected));
      int t = -1, f = -1;
      for (int i = 1; i <= n; i++) {
        if (selected[i] && t == -1) {
          t = i;
        }
        if (!selected[i] && f == -1) {
          f = i;
        }
      }
      if (f != -1 && t != -1) {
        // selected = false 인 것 bfs
        // selected = true 인 것 bfs
        visited = new boolean[11];
        int people1 = bfs(false, f);
        int people2 = bfs(true, t);
        int abs = Math.abs(people1 - people2);

        // 모든 노드가 탐색됐는지 확인
        for (int i = 1; i <= n; i++) {
          if (!visited[i]) {
            return;
          }
        }
//        System.out.println(people1 + " " + people2 + " " + abs);

        if (abs < result) {
          result = abs;
        }
      }

      return;
    }

    selected[idx] = false;
    makeCase(idx + 1);
    selected[idx] = true;
    makeCase(idx + 1);
  }
}
