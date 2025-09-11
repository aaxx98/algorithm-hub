
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

  public static void main(String[] args) throws IOException {
    List<Integer> list = new ArrayList<>();
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int n = Integer.parseInt(br.readLine());
    for (int i = 0; i < n; i++) {
      int a = Integer.parseInt(br.readLine());
      list.add(a);
    }
    Collections.sort(list);
    for (int i : list) {
      bw.write(i + "\n");
    }
 
    bw.flush();
    bw.close();
    br.close();
  }
}
