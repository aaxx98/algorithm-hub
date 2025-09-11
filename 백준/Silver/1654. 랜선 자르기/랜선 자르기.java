
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int k = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken()); // 목표값

    int[] arr = new int[k];
    long lo, hi, result;
    lo = 1;
    hi = 0;
    result = 0;

    for (int i = 0; i < k; i++) {
      arr[i] = Integer.parseInt(br.readLine()); // 랜선 길이
      if (arr[i] > hi) {
        hi = arr[i];
      }
    }

    while (lo <= hi) {
      long mid = (lo + hi) / 2;
      long total = 0;
      for (int a : arr) {
        total += a / mid;
      }
      if (total >= n) {
        lo = mid + 1;
        if (result < mid) {
          result = mid;
        }
      } else {
        hi = mid - 1;
      }
    }
    System.out.println(result);
  }
}
