package crocodile_hunter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import crocodile_hunter.Player;
import crocodile_hunter.Map;

public class main{
	static String strCommands ="Command list:\n"
			+ "n = walk north ^\n"
			+ "e = walk east  >\n"
			+ "s = walk south v\n"
			+ "w = walk west  <\n"
			+ "m = Open map\n"
			+ "h = help\n"
			+ "d = end game\n";
	static String[][] strMoveList={
			{"You walk north.","You walk east","You walk south","You walk west"},
			{"You stand still, as you cannot go further north.",
			"You stand still, as you cannot go further east.",
			"You stand still, as you cannot go further south.",
			"You stand still, as you cannot go further west."}
	};
	static int[][] positionDelta={
			{-1,0,1,0},
			{0,1,0,-1},
	};

	static int[] intCommandList=   {110,101,115,119,109,104,100};

	static int isRunning = 0;
	
	public static void main(String [ ] args) throws IOException{
		
		//boolean isRunning = true;
		int intCommand;
		int intHeight;
		String strMessage=null;
		Scanner scanIn = new Scanner(System.in);
		
		
		
		
		System.out.println(strCommands);
		
		while(isRunning<40){

			System.out.println("Enter command: ");

			// identify command
			intCommand=System.in.read();

			// execute command
			strMessage=Player.executeCommand(intCommand);
			
			
			
			
			
			//This is necessary, but I don't know why
			scanIn.nextLine();
			
			intHeight=Map.intMap[Player.positionY][Player.positionX];
			strMessage+=("\nYou are now standing "+intHeight+" meters above sea level.");
			
			System.out.println(strMessage);
			
			isRunning++;
		}
		scanIn.close();
		
		// The sweet ASCII art was generated by this web site (font: Big): http://patorjk.com/software/taag 
		System.out.println(
				  "You died.\n"
				+ "   _____                                            \n"
				+ "  / ____|                                           \n"
				+ " | |  __  __ _ _ __ ___   ___    _____   _____ _ __ \n"
				+ " | | |_ |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|\n"
				+ " | |__| | (_| | | | | | |  __/ | (_) \\ V /  __/ |   \n"
				+ "  \\_____|\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|   \n\n"
				+ "Thanks for playing!");
		
		/*String[] gameOver={"You died.",
				"   _____                                            ",
				"  / ____|                                           ",
				" | |  __  __ _ _ __ ___   ___    _____   _____ _ __ ",
				" | | |_ |/ _` | '_ ` _ \\ / _ \\  / _ \\ \\ / / _ \\ '__|",
				" | |__| | (_| | | | | | |  __/ | (_) \\ V /  __/ |   ",
				"  \\_____|\\__,_|_| |_| |_|\\___|  \\___/ \\_/ \\___|_|   \n",
				"Thanks for playing!"};
		
		for (int i=0;i<8;i++){
			System.out.println(gameOver[i]);
			try {
			    Thread.sleep(333);                 //1000 milliseconds is one second.
			} catch(InterruptedException ex) {
			    Thread.currentThread().interrupt();
			}
		}
		*/
		
	}
}