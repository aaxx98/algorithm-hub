import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int k = Integer.parseInt(st.nextToken());
    int[][] students = new int[2][7];

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      students[s][y]++;
    }

    int result = 0;

    for (int i = 1; i <= 6; i++) {
      result += students[0][i] / k;
      result += students[1][i] / k;
      if (students[0][i] % k > 0) {
        result++;
      }
      if (students[1][i] % k > 0) {
        result++;
      }
    }

    System.out.println(result);
  }
}
