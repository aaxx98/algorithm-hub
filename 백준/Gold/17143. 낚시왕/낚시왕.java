import java.io.*;
import java.util.*;

public class Main {

  static int R, C, M;
  static int[][] map;
  static HashMap<String, Fish> fishMap = new HashMap<>();
  static int ans = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[R + 1][C + 1];

    for (int i = 0; i < M; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int s = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int z = Integer.parseInt(st.nextToken());
      Fish f = new Fish(r, c, s, d, z);
      fishMap.put(f.getKey(), f);
      map[r][c] = z;
    }

    // 1초씩 오른쪽으로 이동
    for (int col = 1; col <= C; col++) {
      // 1. 상어 잡기
      for (int row = 1; row <= R; row++) {
        String key = row + "_" + col;
        if (fishMap.containsKey(key)) {
          Fish caught = fishMap.remove(key);
          ans += caught.z;
          map[row][col] = 0;
          break;
        }
      }

      // 2. 상어 이동
      HashMap<String, Fish> nextMap = new HashMap<>();
      map = new int[R + 1][C + 1];

      for (Fish fish : fishMap.values()) {
        fish.move(R, C);
        String key = fish.getKey();
        // 겹치면 큰 물고기만 남기기
        if (!nextMap.containsKey(key) || nextMap.get(key).z < fish.z) {
          nextMap.put(key, fish);
          map[fish.r][fish.c] = fish.z;
        }
      }
      fishMap = nextMap;
    }

    System.out.println(ans);
  }
}

class Fish {

  int r, c, s, d, z;
  // d: 1=위,2=아래,3=오른쪽,4=왼쪽

  Fish(int r, int c, int s, int d, int z) {
    this.r = r;
    this.c = c;
    this.s = s;
    this.d = d;
    this.z = z;
  }

  public void move(int R, int C) {
    if (d == 1 || d == 2) {
      int cycle = 2 * (R - 1);
      int move = s % cycle;
      for (int i = 0; i < move; i++) {
        if (d == 1 && r == 1) {
          d = 2;
        } else if (d == 2 && r == R) {
          d = 1;
        }
        r += (d == 1 ? -1 : 1);
      }
    } else {
      int cycle = 2 * (C - 1);
      int move = s % cycle;
      for (int i = 0; i < move; i++) {
        if (d == 4 && c == 1) {
          d = 3;
        } else if (d == 3 && c == C) {
          d = 4;
        }
        c += (d == 3 ? 1 : -1);
      }
    }
  }

  public String getKey() {
    return r + "_" + c;
  }
}
