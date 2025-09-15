import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int n=0;
    static int count=0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        int[] queens = new int[n];
       
        nQueen(0, queens);
        bw.write(String.valueOf(count));

		bw.flush();
		bw.close();
	}
    public static void nQueen(int k, int[] queens){
        if(k==n){
            count++;
            //printQ(queens);
            return;
        }
        for(int i=0;i<n;i++){
    		queens[k]=i;
        	if(k<n && !check(queens,k)) {
        		nQueen(k+1,queens);
        	}        
        }
        return;
    }

    public static boolean check(int[] q, int k){
        for(int i=0;i<k;i++){
        	if(q[k]==q[i]) { 
        		return true;
        	}
        	//대각선
            if(Math.abs(k-i)==Math.abs(q[k]-q[i])) {
            	return true;
            }
        }
        
        return false;
    }
    
    public static void printQ(int[] q) {
    	for(int i=0;i<n;i++) {
    		System.out.print(q[i]+" ");
    	}
    	System.out.println();
    }
}