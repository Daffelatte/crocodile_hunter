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
		
		//create Unit instances

		int[][] intAr2PlayerAttackData={
				//damage, hasBuff?(0=false, 1=true), target(0=self, 1=enemy), buffType, buffLevel, buffDuration
				{0,0}, // punch
				{-1,1,1,7,1,3}, // suffer
				{-1,1,1,5,3,2}, // blindness
				{-2,1,0,2,1,3}, // protection
				{-8,0,0,0,0,0} // item
		};
		String[][] strAr2PlayerAttackData={
				{"punch", "cruel kick", "sweep", "guard", ""},
				{"You punch the crocodile in the face", "You kick the Crocodile in the balls", "You quickly sweep the crocodiles legs", "You bolster your defences", ""},
		};
		int[][] intAr2CrocAttackData={
				//damage, hasBuff?(0=false, 1=true), target(0=self, 1=enemy), buffType, buffLevel, buffDuration
				{0,0}, // punch
				{-1,1,1,7,1,3}, // suffer
				{-1,1,1,5,3,2}, // blindness
				{-2,1,0,2,1,3}, // protection
				{-8,0,0,0,0,0} // item
		};
		String[][]strAr2CrocAttackData={
				{"tailwhip", "bite", "tail-sweep", "guard"},
				{"The Crocodile strikes you with its tail", "The Crocodile bites you", "The crocodile sweeps your legs", "The crocodile bolsters it's defences", ""},
		};
		
		int[] intAr1PlayerHealth={10,9,8,7};
		int[] intAr1PlayerMaxHealth={10,10,10,10};
		int[] intAr1PlayerRunningSpeed={2,2,2,2};
		int[] intAr1PlayerStamina={2,2,2,2};
		int[] intAr1PlayerStealth={0,0,0,0};
		int[] intAr1PlayerDetection={3,3,3,3};
		
		int intPlayerSpeed=1;
		int intPlayerHealth=intAr1PlayerHealth[intDifficulty];
		int intPlayerMaxHealth=intAr1PlayerMaxHealth[intDifficulty];
		int intPlayerDamage=2;
		int intPlayerRunningSpeed=intAr1PlayerRunningSpeed[intDifficulty];
		int intPlayerStamina=intAr1PlayerStamina[intDifficulty];
		int intPlayerStealth=intAr1PlayerStealth[intDifficulty];
		int intPlayerDetection=intAr1PlayerDetection[intDifficulty];
		
		int intCrocSpeed=1;
		int[] intAr1CrocHealth={6,6,6,6};
		int[] intAr1CrocMaxHealth={6,6,6,6};
		int intCrocRunningSpeed=2;
		int intCrocDamage=2;
		int[] intAr1CrocDetection={3,3,3,3};
		
		int intCrocHealth=intAr1CrocHealth[intDifficulty];
		int intCrocMaxHealth=intAr1CrocMaxHealth[intDifficulty];
		int intCrocDetection=intAr1CrocDetection[intDifficulty];
		
		
		Player player = new Player(0,0,"Player",intPlayerSpeed,intPlayerHealth, intPlayerMaxHealth, intPlayerDamage, intAr2PlayerAttackData, strAr2PlayerAttackData, intPlayerRunningSpeed, intPlayerStamina, intPlayerStealth, intPlayerDetection);
		Croc croc = new Croc(0,0,"The Crocodile",intCrocSpeed,intCrocHealth, intPlayerMaxHealth, intPlayerDamage, intAr2CrocAttackData, strAr2CrocAttackData,intCrocRunningSpeed, intCrocDetection);
		
		System.out.println(strCommands);
		
		// Generate intMap
		Map.generateIntMap();
		Map.generateEventMap();
		Map.generateWeaponMap();
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
				if (!player.isRunning && player.stamina<player.maxStamina){
					player.stamina++;
				}
				if (player.stamina<=0){
					System.out.println("Exhausted, You sit down to rest.");
					player.stamina=0;
					player.toggleRun();
					player.isActiveUnit=false;
					System.out.println("Press enter to continue.");
					
					// Listens for next input.
					scanIn.nextLine();
		
					// identify player command
					System.in.read();
				}
				while(player.isActiveUnit){
					System.out.println("HLT:"+player.health+"/"+player.maxHealth+" | STA:"+player.stamina+"/"+player.maxStamina+" | HEI:"+player.height+"/8");
					System.out.println("Enter command: ");
					
					// Listens for next input.
					scanIn.nextLine();
		
					// identify player command
					intPlayerCommand=System.in.read();
					// validate command
					if (Unit.isCommandValid(intPlayerCommand)){
						
						// execute command
						strPlayerMessage=player.executeCommand(intPlayerCommand, player, player, croc);
						
						//This is necessary, but I don't know why
						//scanIn.nextLine();
						
						strPlayerMessage+=player.getHeight(player);
						
						// Print player message
						System.out.println(strPlayerMessage);
	
						// Checks if the player has won
						if (player.checkForMaximum(player,player.positionY,player.positionX)){
							win=true;
							break;
						};
						//checks for events
						if (player.checkForEvent(player.positionY,player.positionX)){
							player.doEvent(Map.intAr2EventMap[player.positionY][player.positionX]);
						};
						//checks for items
						if (player.checkForItem(player.positionY,player.positionX)){
							player.doWeapon(Map.intAr2WeaponMap[player.positionY][player.positionX]);
						};
						
					}else if (!Unit.isCommandValid(intPlayerCommand)){
						System.out.println("\""+Character.toString((char)intPlayerCommand)+strValidCommands+".");
					}
				}
				if (win){
					break;
				}
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
				
				player.isActiveUnit=true;
				first=true;
			}else if (booAr1Gamestate[1]){
				int intPlayerAttack;
				String strCrocValid=null;
				String strPlayerValid=null;
				while(player.health>0 && croc.health>0){
					
					System.out.println("---PLAYER ATTACK---");
					// Player chooses attack
					System.out.println("Choose attack: ");
					scanIn.nextLine();
					intPlayerAttack=System.in.read()-48;
					
					// validate attack
					if (player.validateAttack(intPlayerAttack)){
						
						// player attacks
						player.attack(croc, intPlayerAttack);
						
					}else if (!(strPlayerValid=="valid")){
						System.out.println(strPlayerValid);
					}
					
					// crocodile chooses attack
					int intCrocAttack = croc.chooseAttack(player);
					System.out.println("---CROC ATTACK---");
					// validate attack
					if (croc.validateAttack(intCrocAttack)){
					
						// crocodile attacks
						croc.attack(player, intCrocAttack);
						
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
					
					System.out.println("---BUFFS---");
					// update buffs
					croc.updateBuffs();
					player.updateBuffs();
					croc.checkForIllegalHealth();
					player.checkForIllegalHealth();
					System.out.println("---STATS---");
					System.out.println("Player has "+player.health+" health");
					System.out.println("Crocodile has "+croc.health+" health");
					System.out.println("---END---");
					
					
					try {
					    Thread.sleep(1500);
					} catch(InterruptedException ex) {
					    Thread.currentThread().interrupt();
					}
					
					if (croc.health<=0){
						booAr1Gamestate[1]=false;
						booAr1Gamestate[0]=true;
						croc.health = intAr1CrocHealth[main.intDifficulty];
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