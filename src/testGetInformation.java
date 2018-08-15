
import org.json.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class testGetInformation {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		System.out.print("Input the name: ");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		SummonerInformation summoner = new SummonerInformation(name);
		CurrentGameInformation current = new CurrentGameInformation(name);
		
		for(int i = 0; i < 10; i++) {
			
			System.out.print(current.getParticipantName(i) + ": ");
			if(current.getTier("" + current.getParticipantId(i)) == "0") {
				System.out.println("Never Ranked!");
			}
			else {
				System.out.println(current.getTier("" + current.getParticipantId(i)) + " " + current.getDivision("" + current.getParticipantId(i)));
			}
			if(i == 4) {
				System.out.print("\n");
			}
			
		}
	}

}
