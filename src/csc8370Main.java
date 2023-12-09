import csc8370.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class csc8370Main {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		System.out.print("Who are you? Server or Client? : ");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String ans = bf.readLine();
		if(ans.equalsIgnoreCase("server")){
			 new Server(5123);
		}
		else{
			new Client();
		}
	}

}
