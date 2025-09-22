class Solution {

  static int[] arr = new int[5];
  static int[][] Q;
  static int[] ANS;
  static int N;
  static int RET;

  public int solution(int n, int[][] q, int[] ans) {
    N = n;
    Q = q;
    ANS = ans;
    RET = 0;

    makeArr(0, 1);

    return RET;
  }

  public void makeArr(int depth, int num) {
    if (depth == 5) {
      // 값체크
      for (int i = 0; i < ANS.length; i++) {
        int count = 0;
        for (int j = 0; j < 5; j++) {
          if (has(Q[i][j])) {
            count++;
          }
        }
        if (count != ANS[i]) {
          return;
        }
      }
      RET++;
      return;
    }

    for (int i = num; i <= N; i++) {
      arr[depth] = i;
      makeArr(depth + 1, i + 1);
    }
  }

  public boolean has(int num) {
    for (int i = 0; i < 5; i++) {
      if (arr[i] == num) {
        return true;
      }
    }
    return false;
  }
}