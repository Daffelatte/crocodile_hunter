package crocodile_hunter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import crocodile_hunter.Unit;
import crocodile_hunter.Player;
import crocodile_hunter.Croc;
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
	

	static int[] intAr1Command=   {110,101,115,119,109,104,100,-643,116};

	static boolean booIsRunning = true;
	static int intFailsafe = 0;
	
	static boolean[] booAr1Difficulty={false,false,false,false};
	static String[] strAr1Difficulty={"easy", "medium", "hard", "Extremly hard"};
	static int intDifficulty;
	//public static boolean[] alwaysGenerateMapList={true,false,false,false};;
	public static boolean alwaysGenerateMap;
	public static boolean allowMap;
	public static boolean alwaysHunt;
	public static boolean showCrocOnMap;
	public static boolean showHeightOnMap;
	public static String strValidCommands="\" is an invalid command.\n"
			+ "Valid commands are: n, e, s, w, h, d, t";
	public static boolean[] booAr1Gamestate={true, false};


	public static boolean first;
	
	public static void main(String [ ] args) throws IOException{
		
		int intPlayerCommand = 0;
		int intCrocCommand;
		int intHeight;
		boolean win=false;
		String strPlayerMessage=null;
		String strCrocMessage=null;
		
		Scanner scanIn = new Scanner(System.in);

		// Choose difficulty
		System.out.println("Choose difficulty:\n"
						 + "| 0=easy | 1=medium | 2=hard | 3=Extremly hard |");
		
		while(true){
			
			// identify difficulty
			intDifficulty = System.in.read()-48;
			
			// Confirm that identity has been chosen
			if (0<=intDifficulty && intDifficulty <=3){
				booAr1Difficulty[intDifficulty]=true;
				System.out.println("You have chosen: \""+strAr1Difficulty[intDifficulty]+"\".\n");
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
		int CrocY = (int) Math.floor((Math.random()*3));
		int CrocX = (int) Math.floor((Math.random()*3)+7);
		*/
		//int playerY = (int) Math.floor((Math.random()*9));
		//int playerX = (int) Math.floor((Math.random()*9));
		//int CrocY = (int) Math.floor((Math.random()*9));
		//int CrocX = (int) Math.floor((Math.random()*9));
		//create Unit instances

		
		String[] strAr1PlayerAttack={"punch (1)", "kick (2)", "sweep (3)", "rising sun (4)", "setting sun (5)"};
		String[] strAr1PlayerAttackText={"You punch the crocodile in the face", "You kick the crocodile in the chest", "You quickly sweep the crocodiles legs", "You grab and lift your opponent into the air", "You slam your opponent into the ground"};
		String strPlayerRestrainedText="You are restrained, and cannot attack";
		String[] strAr1CrocAttack={"tail whip (1)", "headbutt (2)", "tail-sweep (3)", "bite (4)", "ravage (5)"};
		String[] strAr1CrocAttackText={"A spinning tail kick to the head", "Slam your head into your opponents chest", "A quick legsweep", "Bite and hold your opponent in your jaws", "Violently shake, tearing appart your opponent"};
		String strCrocRestrainedText="The crocodile is restrained, and cannot attack";
		
		int[] intAr1PlayerHealth={6,4,2,1};
		int[] intAr1CrocHealth={6,8,10,12};
		
		Player player = new Player(0,0,1,intAr1PlayerHealth,2, strAr1PlayerAttack, strAr1PlayerAttackText, strPlayerRestrainedText);
		Croc croc = new Croc(0,0,2,intAr1CrocHealth,2, strAr1CrocAttack, strAr1CrocAttackText, strCrocRestrainedText);
		
		System.out.println(strCommands);
		
		// Generate intMap
		Map.generateIntMap();
		if (alwaysGenerateMap){
			System.out.println(Map.generateStrMap(player, croc, Map.intAr2ActiveMap, Map.strAr1ActiveASCII));
		}
		
		// Relocate player
		player.reLocate(player, player, croc);
		
		// Relocate crocodile
		croc.reLocate(croc, player, croc);
		
		System.out.println("You and the crocodile have been relocated");
		
		if (alwaysGenerateMap){
			System.out.println(Map.generateStrMap(player, croc, Map.intAr2ActiveMap, Map.strAr1ActiveASCII));
		}

		while(booIsRunning){
			//Check game state
			if (booAr1Gamestate[0]){
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
					strPlayerMessage=player.executeCommand(intPlayerCommand, player, player, croc);
					
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
					intCrocCommand=croc.chooseCommand(player, croc);
					
					// execute command
					strCrocMessage=croc.executeCommand(intCrocCommand, croc, player, croc);
					
					// compare player and crocodile positions
					strCrocMessage+="\n"+croc.checkForPlayer(player, croc);
					
					System.out.println(strCrocMessage);
					
					if (alwaysGenerateMap){
						System.out.println(Map.generateStrMap(player, croc, Map.intAr2ActiveMap, Map.strAr1ActiveASCII));
					}
					
				}else if (!Unit.isCommandValid(intPlayerCommand)){
					System.out.println("\""+Character.toString((char)intPlayerCommand)+strValidCommands+".");
				};
				
			first=true;
			}else if (booAr1Gamestate[1]){
				int intPlayerAttack;
				String strCrocValid=null;
				String strPlayerValid=null;
				while(player.health>0 && croc.health>0){
					
					// crocodile chooses attack
					int intCrocAttack = croc.chooseAttack(player);
					
					// validate attack
					strCrocValid=croc.validateAttack(intCrocAttack);
					if (strCrocValid=="valid"){
					
						// crocodile attacks
						System.out.println(croc.attack(player, intCrocAttack));
						
					}else if (!(strCrocValid=="valid")){
						System.out.println(strCrocValid);
					};
					
					
					if (player.health<=0){
						System.out.println("You have taken fatal damage!");
						try {
						    Thread.sleep(1500);
						} catch(InterruptedException ex) {
						    Thread.currentThread().interrupt();
						}
						break;
					}
					
					// Player chooses attack
					System.out.println("Choose attack: ");
					scanIn.nextLine();
					intPlayerAttack=System.in.read()-48;
					
					// validate attack
					strPlayerValid=player.validateAttack(intCrocAttack);
					if (strPlayerValid=="valid"){
						
						// player attacks
						System.out.println(player.attack(croc, intPlayerAttack));
						
					}else if (!(strPlayerValid=="valid")){
						System.out.println(strPlayerValid);
					}
					
					try {
					    Thread.sleep(1500);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					
					if (croc.health<=0){
						booAr1Gamestate[1]=false;
						booAr1Gamestate[0]=true;
						if(croc.intRage<=5){
							croc.intRage++;
						}
						croc.health = intAr1CrocHealth[main.intDifficulty]-croc.intRage;
						croc.reLocate(croc, player, croc);
						System.out.println("Defeted, The crocodile flees to recover strengh.");
						break;
					}
				}
			}
			
			// This ensures that the while loop is not infinite.
			intFailsafe++;
			if (intFailsafe>200){
				break;
			}
		}
		
		System.out.println(Map.generateStrMap(player, croc, Map.intAr2ActiveMap, Map.strAr1FullASCII));
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