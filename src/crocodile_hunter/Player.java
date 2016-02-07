package crocodile_hunter;

import java.util.Arrays;

public class Player extends Unit {

	public String[][] strMoveList={
			{"You walk north.","You walk east","You walk south","You walk west"},
			{"You stand still, as you cannot go further north.",
			"You stand still, as you cannot go further east.",
			"You stand still, as you cannot go further south.",
			"You stand still, as you cannot go further west."},
			{"You run north.","You run east","You run south","You run west"}
	};
	public boolean exhasted=false;
	
	public Player(int startX, int startY, int speed) {
		super(startX, startY, speed);
		// TODO Auto-generated constructor stub
	}
	public String getHeight(Player player) {
		int intHeight = Math.abs(9-Map.intActiveMap[player.positionY][player.positionX]);
		String strReturn = ("\nYou are now standing "+intHeight+" meters above sea level.");
		return strReturn;
	}
	public boolean checkForMaximum(Player player, int positionY, int positionX) {
		return Map.intActiveMap[player.positionY][player.positionX]==1;
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

}
