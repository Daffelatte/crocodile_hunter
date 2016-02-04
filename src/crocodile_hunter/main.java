package crocodile_hunter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import crocodile_hunter.Player;
import crocodile_hunter.Map;

public class main{
	public static void main(String [ ] args) throws IOException{
		
		//boolean isRunning = true;
		int isRunning = 0;
		int intCommand;
		int intHeight;
		String strMessage;
		Scanner scanIn = new Scanner(System.in);
		String[] strCommandList={"n","e","s","w","h","m","d"};
		int[] intCommandList=	{110,101,115,119,104,109,100};
		String strCommands ="Command list:\n"
				+ "n = walk north ^\n"
				+ "e = walk east  >\n"
				+ "s = walk south v\n"
				+ "w = walk west  <\n"
				+ "h = help\n"
				+ "m = Open map\n"
				+ "d = end game\n";
		
		System.out.println(strCommands);
		
		while(isRunning<40){

			System.out.println("Enter command: ");
			intCommand=System.in.read();
			
			//Player.executeCommand(intCommand);
			
			for (int i=0;intCommandList.length<i;i++){
				if (intCommandList[i]==intCommand){
					// You walk strCommandList[i] .
					break;
				}
			}
			
			// identify command
			
			// execute command
			
			//System.out.println(intCommand);
			if (intCommand==104){
				strMessage=strCommands;
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
			
			intHeight=Map.intMap[Player.positionY][Player.positionX];
			strMessage+=("\nYou are now standing "+intHeight+" meters above sea level.");
			
			System.out.println(strMessage);
			
			isRunning++;
		}
		scanIn.close();

		System.out.println("You died.");
		
		
	}
}