import java.util.Arrays;

class Solution {

  public int solution(int[] people, int limit) {
    Arrays.sort(people);

    int ans = 0;
    int left = 0, right = people.length - 1;
    while (left <= right) {
      if (limit - people[right] < people[left]) {
        right--;
        ans++;
      } else {
        left++;
        right--;
        ans++;
      }
    }

    return ans;
  }
}
