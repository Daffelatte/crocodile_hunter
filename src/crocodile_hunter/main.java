package crocodile_hunter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import crocodile_hunter.Unit;
import crocodile_hunter.Player;
import crocodile_hunter.Crocodile;
import crocodile_hunter.Map;

public class main{
	public static String strCommands ="Command list:\n"
			+ "n = walk north ^\n"
			+ "e = walk east  >\n"
			+ "s = walk south v\n"
			+ "w = walk west  <\n"
			+ "h = help\n"
			+ "d = end game\n"
			+ "t = toggle run\n";
	

	static int[] intCommandList=   {110,101,115,119,109,104,100,-643,116};

	static boolean booIsRunning = true;
	static int intFailsafe = 0;
	
	static boolean[] booDifficultyList={false,false,false,false};
	static String[] strDifficultyList={"easy", "medium", "hard", "Extremly hard"};
	static int intDifficulty;
	//public static boolean[] alwaysGenerateMapList={true,false,false,false};;
	public static boolean alwaysGenerateMap;
	public static boolean allowMap;
	public static boolean alwaysHunt;
	public static boolean showCrocodileOnMap;
	public static boolean showHeightOnMap;
	public static String strValidCommands="\" is an invalid command.\n"
			+ "Valid commands are: n, e, s, w, h, d, t";
	public static boolean[] booGamestateList={true, false};
	
	public static void main(String [ ] args) throws IOException{
		
		int intPlayerCommand = 0;
		int intCrocodileCommand;
		int intHeight;
		boolean win=false;
		String strPlayerMessage=null;
		String strCrocodileMessage=null;
		Scanner scanIn = new Scanner(System.in);

		// Choose difficulty
		System.out.println("Choose difficulty:\n"
						 + "| 0=easy | 1=medium | 2=hard | 3=Extremly hard |");
		
		while(true){
			
			// identify difficulty
			intDifficulty = System.in.read();
			
			// Confirm that identity has been chosen
			if (0<=intDifficulty-48 && intDifficulty-48 <=3){
				booDifficultyList[intDifficulty-48]=true;
				System.out.println("You have chosen: \""+strDifficultyList[intDifficulty-48]+"\".\n");
				break;
			}else{
				System.out.println("\""+Character.toString((char)intDifficulty)+"\" is an invalid difficulty.\n"
						+ "Valid difficulty are: 0, 1, 2, 3\n"
						+ "Choose difficulty:\n");
			}
			// Listens for next input.
			scanIn.nextLine();
			
		}
		//set difficulty variables
		Unit.setDifficulty(intDifficulty);
		/*
		int playerY = (int) Math.floor((Math.random()*3)+7);
		int playerX = (int) Math.floor((Math.random()*3));
		int CrocodileY = (int) Math.floor((Math.random()*3));
		int CrocodileX = (int) Math.floor((Math.random()*3)+7);
		*/
		//int playerY = (int) Math.floor((Math.random()*9));
		//int playerX = (int) Math.floor((Math.random()*9));
		//int CrocodileY = (int) Math.floor((Math.random()*9));
		//int CrocodileX = (int) Math.floor((Math.random()*9));
		//create Unit instances
		Player player = new Player(0,0,1,1);
		Crocodile crocodile = new Crocodile(0,0,2,1);
		
		System.out.println(strCommands);
		
		// Generate intMap
		Map.generateIntMap();
		if (alwaysGenerateMap){
			System.out.println(Map.generateStrMap(player, crocodile, Map.intActiveMap, Map.strActiveASCIIList));
		}
		
		// Relocate player
		player.reLocate(player, player, crocodile);
		
		// Relocate crocodile
		crocodile.reLocate(crocodile, player, crocodile);
		
		System.out.println("You and the crocodile have been relocated");
		
		if (alwaysGenerateMap){
			System.out.println(Map.generateStrMap(player, crocodile, Map.intActiveMap, Map.strActiveASCIIList));
		}

		while(booIsRunning){
			//Check game state
			if (booGamestateList[0]){
				if (!player.exhasted){
					System.out.println("Enter command: ");
					
					// Listens for next input.
					scanIn.nextLine();
		
					// identify player command
					intPlayerCommand=System.in.read();
				}else if (player.exhasted){
					intPlayerCommand=116;
					player.exhasted=false;
				}
				// validate command
				if (Unit.isCommandValid(intPlayerCommand)){
					
					// execute command
					strPlayerMessage=player.executeCommand(intPlayerCommand, player, player, crocodile);
					
					// Checks if the player has won
					if (player.checkForMaximum(player,player.positionY,player.positionX)){
						win=true;
						break;
					};
					
					//This is necessary, but I don't know why
					//scanIn.nextLine();
					
					strPlayerMessage+=player.getHeight(player);
					
					// Print player message
					System.out.println(strPlayerMessage);
					
					// Choose crocodile command
					intCrocodileCommand=crocodile.chooseCommand(player, crocodile);
					
					// execute command
					strCrocodileMessage=crocodile.executeCommand(intCrocodileCommand, crocodile, player, crocodile);
					
					// compare player and crocodile positions
					strCrocodileMessage+="\n"+crocodile.checkForPlayer(player, crocodile);
					
					System.out.println(strCrocodileMessage);
					
					if (alwaysGenerateMap){
						System.out.println(Map.generateStrMap(player, crocodile, Map.intActiveMap, Map.strActiveASCIIList));
					}
					
				}else if (!Unit.isCommandValid(intPlayerCommand)){
					System.out.println("\""+Character.toString((char)intPlayerCommand)+strValidCommands+".");
				};
				
			player.health=1;
			}else if (booGamestateList[1]){
				int intPlayerAttack;
				while(player.health>0 && crocodile.health>0){
				System.out.println("Choose attack: ");
				
				// Listens for next input.
				scanIn.nextLine();
		
				// identify player attack
				intPlayerAttack=System.in.read();
				
				// crocodile chooses attack
				int intCrocodileAttack = crocodile.chooseAttack();
				
				// crocodile attacks
				// player defends (has X millisecounds to choose a block ( low/mid/high/spam ENTER )
				crocodile.fight(crocodile, player, player, crocodile, intCrocodileAttack);
				
				// Listens for next input.
				String s=scanIn.nextLine();
				
				// player attacks
				// crocodile defends (RNG)
				player.fight(player, crocodile, player, crocodile, intCrocodileAttack);
				
				}
			}
			
			// This ensures that the while loop is not infinite.
			intFailsafe++;
			if (intFailsafe>200){
				break;
			}
		}
		
		System.out.println(Map.generateStrMap(player, crocodile, Map.intActiveMap, Map.strFullASCIIList));
		scanIn.close();
		if (win){
			// The sweet ASCII art was generated by this web site (font: Big): http://patorjk.com/software/taag 
			System.out.println(
					  "You reached the top of the mountain!\n"
					+ "__     __                    _      \n" 
					+ "\\ \\   / /                   (_)      \n"
					+ " \\ \\_/ /__  _   _  __      ___ _ __  \n"
					+ "  \\   / _ \\| | | | \\ \\ /\\ / / | '_ \\ \n"
					+ "   | | (_) | |_| |  \\ V  V /| | | | |\n"
					+ "   |_|\\___/ \\__,_|   \\_/\\_/ |_|_| |_|\n\n"
					+ "Thanks for playing!");
		}else if(!win){
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
}