# 분할정복 (Divide & Conquer)

- 문제를 동일하거나 유사한 하위 문제들로 분할 → 각 하위 문제 해결 → 결과를 합쳐 전체 문제 해결
- **구현 시 주의사항**

  - 기저조건(base condition)을 명확히
  - 재귀 깊이/스택 주의
  - 합치는 과정에서 불필요한 복사 제거(배열 참조/인덱스 사용)
  - 분할 가능한 경계에 대해 정확히 반쪽으로 나누어야 최소 시간복잡도가 됨

## 1. Merge Sort (정렬형 분할정복)

### [Merge Sort 관련 문제](./merge-sort/README.md)

배열을 반으로 나누고, 정렬된 두 부분을 병합하여 정렬된 배열을 만드는 분할정복 알고리즘

- base condition: 분할 된 array의 길이가 모두 1일 때 → merge: [n, m] (n<m)
  - [m], [n, …] / [n], [m, …]으로 분할 되었을 때 → merge: [n, m, …] (n<m)
- mergeSort: 배열을 반으로 나눈 후, 병합(merge)
- merge: 배열 병합
  - base condition에서 부터, merge된 배열은 모두 정렬되어있음
  - 분할 된 배열의 첫번째 원소 arr[i], arr[j]를 비교하여 더 작은것을 temp[k]에 넣음
    - index 증가시켜서 비교
      - left~mid - i
      - mid+1~right - j
      - temp - k
  - 분할 된 두 배열중 하나의 순회가 끝나면 나머지 배열은 정렬되어있으므로 그대로 temp에 옮김
  - temp가 완성되면 arr[left:right]에 temp 배열을 복사

### 예제 코드 (Java)

```java
public class MergeSort {
    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);

        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) temp[k++] = arr[i++];
            else temp[k++] = arr[j++];
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        System.arraycopy(temp, 0, arr, left, temp.length);
    }
}
```
