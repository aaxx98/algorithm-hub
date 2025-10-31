import java.util.Arrays;
import java.util.Objects;

class Solution {

  static int count = 0, zero = 0;

  public int[] solution(String s) {

    int num;
    String str = s;
    while (!Objects.equals(str, "1")) {
      count++;
      num = delZero(str);
      zero += num;
      str = Integer.toBinaryString(str.length() - num);
    }

    int[] answer = {count, zero};
    return answer;
  }

  private int delZero(String s) {
    int zero = 0;
    char[] arr = s.toCharArray();
    for (char c : arr) {
      if (c == '0') {
        zero++;
      }
    }

    return zero;
  }
}