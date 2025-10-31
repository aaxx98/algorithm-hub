import java.io.*;
import java.util.HashMap;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		int n, m, num;
		n = Integer.parseInt(br.readLine());
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>(n+1);
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			num=Integer.parseInt(st.nextToken());
			if(map.containsKey(num)) {
				map.put(num, map.get(num)+1);
			}
			else {
				map.put(num, 1);
			}
		}
		m = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<m;i++) {
			num=Integer.parseInt(st.nextToken());
			if(map.containsKey(num)) {
				bw.write(map.get(num)+" ");
			}
			else {
				bw.write("0 ");
			}
		}
	
		bw.flush();
		bw.close();
    }
}