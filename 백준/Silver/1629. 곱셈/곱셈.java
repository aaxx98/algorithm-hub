
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

    long a = Long.parseLong(st.nextToken());
    long b = Long.parseLong(st.nextToken());
    long c = Long.parseLong(st.nextToken());

    bw.write(mod(a, b, c) + " ");

    bw.flush();
    bw.close();
    br.close();
  }

  static long mod(long a, long b, long c) {
    if (b == 0) {
      return 1 % c;
    }
    if (b == 1) {
      return a % c;
    }

    long half = mod(a, b / 2, c);
    if (b % 2 == 0) {
      return half * half % c;
    } else {
      return (half * half % c) * a % c;
    }
  }
}
