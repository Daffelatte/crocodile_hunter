package crocodile_hunter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Player extends Unit {

	public String[][] strAr2Move={
			{"You walk north.","You walk east","You walk south","You walk west"},
			{"You stand still, as you cannot go further north.",
			"You stand still, as you cannot go further east.",
			"You stand still, as you cannot go further south.",
			"You stand still, as you cannot go further west."},
			{"You run north.","You run east","You run south","You run west"}
	};
	public boolean exhasted=false;
	
	public Player(int startX, int startY, int speed, int[] intAr1Health, int damage, String[] strAr1Attack, String[] strAr1AttackText) {
		super(startX, startY, speed, intAr1Health, damage, strAr1Attack, strAr1AttackText);
		// TODO Auto-generated constructor stub
	}
	public String getHeight(Player player) {
		int intHeight = Math.abs(9-Map.intAr2ActiveMap[player.positionY][player.positionX]);
		String strReturn = ("\nYou are now standing "+intHeight+" meters above sea level.");
		return strReturn;
	}
	public boolean checkForMaximum(Player player, int positionY, int positionX) {
		return Map.intAr2ActiveMap[player.positionY][player.positionX]==1;
	}
	public String toggleRun(Player player){
		String strReturn=null;
		if (player.speed==1){
			player.speed=4;
			strReturn="You prepare to run";
			
		}else if (player.speed==4){
			player.speed=1;
			strReturn="Exhasted from the run, you stop to catch your breath.";
		}
		player.changeSpeed(player);
		return strReturn;
	}
	public String attack(Croc defender, int intPlayerAttack) {
		// TODO Auto-generated method stub
		
		return defender.defend(this, intPlayerAttack);
	}
	public String defend(Unit attacker, int intAttack) {
		// TODO Auto-generated method stub
		Scanner scanIn = new Scanner(System.in);
		int intPlayerCommand = 0;
		String strReturn = null;
		int[] intAr1DefCommand={1,2,3,4};

		scanIn.nextLine();
		try {
			intPlayerCommand = System.in.read()-48;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (intPlayerCommand==intAr1DefCommand[intAttack]){
			strReturn="You evaded the attack!";
		}else if(!(intPlayerCommand==intAr1DefCommand[intAttack])){
			this.health-=attacker.damage;
			strReturn="You take "+attacker.damage+" damage!";
		};
		

		System.out.print(intPlayerCommand+" == "+intAr1DefCommand[intAttack]+"\n");

		return strReturn;
	}

}
