package crocodile_hunter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import crocodile_hunter.Player;

public class main{
	public static void main(String [ ] args) throws IOException{
		
		//boolean isRunning = true;
		int isRunning = 0;
		int intCommand;
		String strMessage;
		Scanner scanIn = new Scanner(System.in);
		
		System.out.println("Command list:\nn = walk north ^\ne = walk east  >\ns = walk south v\nw = walk west  <\nh = hold still\nm = Open map\nd = end game\n");
		
		while(isRunning<40){

			System.out.println("Enter command: ");
			intCommand=System.in.read();
			//System.out.println(intCommand);
			if (intCommand==104){
				strMessage="You stand still.";
			}else if (intCommand==109){
				String k = "\n_|0|1|2|3|4|5|6|7|8|9|\n";
				for(int i=0;i<10;i++) {
		        	k+=i+"|";
		            for(int j=0;j<10;j++) {
		            	if (Player.playerMap[i][j])
		            		k+="X|";
		            	else{
		            		k+="_|";
		            	}
		            }
		        	k+="\n";
		        }
				strMessage="You look at your map.\n"+k;
			}else if(intCommand==100){
				strMessage="You embrace death.";
				isRunning=40;
			}else{
				strMessage=Player.walk(intCommand);
			}
			//System.out.println("scan in: "+scanIn.nextLine());
			
			//This is necessary, but I don't know why
			scanIn.nextLine();
			
			System.out.println(strMessage);
			
			isRunning++;
		}
		scanIn.close();

		System.out.println("You died.");
		
		
	}
}