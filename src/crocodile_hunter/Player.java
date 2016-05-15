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
	public int[][] intAr2AttackData={
			//damage, hasBuff?(0=false, 1=true), target(0=self, 1=enemy), buffType, buffLevel, buffDuration
			{0,0}, // punch
			{-1,1,1,7,1,3}, // suffer
			{-1,1,1,5,6,2}, // blindness
			{-2,1,0,2,1,3} // protection
	};
	
	public Player(int startX, int startY, String name, int speed, int[] intAr1Health, int damage, String[] strAr1Attack, String[] strAr1AttackText, String strRestrainedText) {
		super(startX, startY, name, speed, intAr1Health, damage, strAr1Attack, strAr1AttackText, strRestrainedText);
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
	public boolean checkForEvent(int positionY, int positionX){
		return Map.intAr2EventMap[this.positionY][this.positionX]!=-1;
	}
	public void doEvent(int selectedEvent){
		Scanner scanIn = new Scanner(System.in);
		int intPlayerAnswer = 0;
		
		scanIn.nextLine();
		
		System.out.println(Map.strEventData[selectedEvent][0]);
		while(true){
			try {
				intPlayerAnswer = System.in.read()-48;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// Confirm that answer is valid
			if (intPlayerAnswer==73){
				System.out.println(Map.strEventData[selectedEvent][1]);
				int intRandomNegative = (int) Math.floor((Math.random()*3)+1);
				if (intRandomNegative != 3){
					intRandomNegative = 0;
				}
				int intBuffType = Map.intEventData[selectedEvent][0 + intRandomNegative];
				int intBuffLevel = Map.intEventData[selectedEvent][1 + intRandomNegative];
				int intBuffDuration = Map.intEventData[selectedEvent][2 + intRandomNegative];
				this.applyBuff(intBuffType, intBuffLevel, intBuffDuration);
				break;
			}else if(intPlayerAnswer==62){
				System.out.println(Map.strEventData[selectedEvent][2]);
				break;
			}else{
				System.out.println("\""+Character.toString((char)intPlayerAnswer)+"\" is an invalid answer.\n"
						+ "Valid answers are: y, n\n"
						+ "Choose answer:\n");
			}
			// Listens for next input.
			scanIn.nextLine();
		}
		//
	}
	/*
	public String attack(Croc defender, int intAttack) {
		String strReturn=null;
		System.out.println(strAr1AttackText[intAttack-1]);
		strReturn=defender.defend(this, intAttack);
		return strReturn;
		
	}
	public String defend(Unit attacker, int intAttack) {
		// TODO Auto-generated method stub
		Scanner scanIn = new Scanner(System.in);
		int intPlayerCommand = 0;
		String strReturn = null;
		int[] intAr1DefCommand={1,2,3,4,5};

		scanIn.nextLine();
		try {
			intPlayerCommand = System.in.read()-48;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (intPlayerCommand==intAr1DefCommand[intAttack]){
			if (this.booDisarmed){
				this.booDisarmed=false;
				strReturn="You breaks free";
			}else if (!(intAttack==3)){
				strReturn="You evaded the attack!";
			}
		}else if(!(intPlayerCommand==intAr1DefCommand[intAttack])){
			if (intAttack==3){
				this.booDisarmed=true;
				strReturn="You are disarmed!";
			}else if (intAttack==4){
				this.booDisarmed=false;
				this.health-=2*attacker.damage;
				strReturn="You take "+2*attacker.damage+" damage!";
			}else{
				//System.out.println(intAttack+"!=4");
				this.health-=attacker.damage;
				strReturn="You take "+attacker.damage+" damage!";
			}
		};
		

		//System.out.print(intPlayerCommand+" == "+intAr1DefCommand[intAttack]+"\n");

		return strReturn;
	}
	*/
}
