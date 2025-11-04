import java.util.Arrays;

class Solution {

  public int solution(int[] A, int[] B) {
    int answer = 0;
    int i = A.length - 1, j = B.length - 1;
    Arrays.sort(A);
    Arrays.sort(B);
    while (i >= 0) {
//      System.out.println(A[i] + " " + B[j]);
      if (A[i] < B[j]) {
        answer++;
        j--;
      }
      i--;
    }
    return answer;
  }
}