import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    StringTokenizer st = new StringTokenizer(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      hashMap.put(arr[i], hashMap.get(arr[i]) == null ? 1 : hashMap.get(arr[i]) + 1);
    }
    Arrays.sort(arr);

    int m = Integer.parseInt(br.readLine());
    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < m; i++) {
      int target = Integer.parseInt(st.nextToken());
      int idx = binarySearch(target, arr);
      if (idx == -1) {
        bw.write("0 ");
      } else {
        bw.write(hashMap.get(target) + " ");
      }
    }

    bw.flush();
    bw.close();
    br.close();
  }

  public static int binarySearch(int target, int[] arr) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
      int mid = (left + right) / 2;
      if (arr[mid] == target) {
        return mid;
      } else if (arr[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }
}
