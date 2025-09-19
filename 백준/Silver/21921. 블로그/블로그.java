import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    StringTokenizer st = new StringTokenizer(br.readLine());

    int x = Integer.parseInt(st.nextToken());
    int n = Integer.parseInt(st.nextToken());
    int[] visitors = new int[x];

    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < x; i++) {
      visitors[i] = Integer.parseInt(st.nextToken());
    }

    int sum = 0, maxSum, total;
    int start = 0, end = n - 1;
    for (int i = 0; i < n; i++) {
      sum += visitors[i];
    }
    maxSum = sum;
    total = 1;

    while (end + 1 < visitors.length) {
      int next = visitors[end + 1];
      int pre = visitors[start];
      sum = sum + next - pre;
      if (maxSum == sum) {
        total++;
      }
      if (sum > maxSum) {
        total = 1;
        maxSum = sum;
      }
      start++;
      end++;
    }

    if (maxSum == 0) {
      bw.write("SAD");
    } else {
      bw.write(maxSum + "\n");
      bw.write(total + "");
    }

    bw.flush();
    bw.close();
    br.close();
  }
}
