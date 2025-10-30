import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    int[] arr = new int[n];
    for (int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(br.readLine());
    }

    mergeSort(arr, 0, n - 1);

    for (int i : arr) {
      bw.write(i + "\n");
    }
    bw.flush();
    bw.close();
    br.close();
  }

  public static void mergeSort(int[] arr, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = (left + right) / 2;

    mergeSort(arr, left, mid);
    mergeSort(arr, mid + 1, right);

    merge(arr, left, mid, right);
  }

  public static void merge(int[] arr, int left, int mid, int right) {
    int[] tmp = new int[right - left + 1];
    int i = left, j = mid + 1, k = 0;

    while (i <= mid && j <= right) {
      if (arr[i] >= arr[j]) {
        tmp[k++] = arr[j++];
      } else {
        tmp[k++] = arr[i++];
      }
    }

    while (i <= mid) {
      tmp[k++] = arr[i++];
    }
    while (j <= right) {
      tmp[k++] = arr[j++];
    }

    System.arraycopy(tmp, 0, arr, left, tmp.length);
  }
}
