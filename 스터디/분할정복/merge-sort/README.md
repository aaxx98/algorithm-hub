# 관련 문제

## **[2751 - 수 정렬하기 2](https://www.acmicpc.net/problem/2751)**

- 머지 소트 구현으로 풀 수 있는 문제

## **[1517 - 버블 소트](https://www.acmicpc.net/problem/1517)**

### bubble sort, merge sort의 inversion

- bubble sort와 merge sort 모두 안정 정렬
  - 값이 같은 원소들의 순서를 유지
- bubble sort와 swap 횟수는 merge sort의 inversion(역순 쌍) 개수와 같음

  - inversoin: merge 과정에서 오른쪽 배열의 요소가 먼저 선택되면, 왼쪽 배열에 남아있는 모든 원소가 뒤로 밀리므로 왼쪽 배열의 length가 swap횟수가 된다.

- 두 방식의 swap 횟수 비교

  ```java
  import java.util.Arrays;

  public class InversionCompare {

    // =======================
    // 버블 소트로 inversion 세기
    // =======================
    public static long bubbleSwapCount(int[] arr) {
      long count = 0;
      int n = arr.length;
      int[] copy = Arrays.copyOf(arr, n); // 원본 배열 보호

      for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
          if (copy[j] > copy[j + 1]) {
            // swap
            int tmp = copy[j];
            copy[j] = copy[j + 1];
            copy[j + 1] = tmp;
            count++;
          }
        }
      }
      return count;
    }

    // =======================
    // Merge Sort로 inversion 세기
    // =======================
    public static long mergeSwapCount(int[] arr) {
      int[] copy = Arrays.copyOf(arr, arr.length);
      long[] count = new long[1]; // count를 배열로 만들어 참조 전달
      mergeSort(copy, 0, copy.length - 1, count);
      return count[0];
    }

    private static void mergeSort(int[] arr, int left, int right, long[] count) {
      if (left >= right) {
        return;
      }
      int mid = (left + right) / 2;

      mergeSort(arr, left, mid, count);
      mergeSort(arr, mid + 1, right, count);
      merge(arr, left, mid, right, count);
    }

    private static void merge(int[] arr, int left, int mid, int right, long[] count) {
      int[] tmp = new int[right - left + 1];
      int i = left, j = mid + 1, k = 0;

      while (i <= mid && j <= right) {
        if (arr[i] <= arr[j]) {
          tmp[k++] = arr[i++];
        } else {
          tmp[k++] = arr[j++];
          count[0] += (mid - i + 1); // 왼쪽 배열에 남은 원소 수만큼 inversion
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

    // =======================
    // main
    // =======================
    public static void main(String[] args) {
      int[] arr = {9, 10, 6, 7, 8, 5, 3, 2, 4, 1};

      System.out.println("원본 배열: " + Arrays.toString(arr));

      long bubbleCount = bubbleSwapCount(arr);
      long mergeCount = mergeSwapCount(arr);

      System.out.println("Bubbl sort swap count: " + bubbleCount);
      System.out.println("Merge Sort inversion count: " + mergeCount);
    }
  }
  ```
