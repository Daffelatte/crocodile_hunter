package crocodile_hunter;

import java.util.Arrays;

public class Player extends Unit {

	public String[][] strMoveList={
			{"You walk north.","You walk east","You walk south","You walk west"},
			{"You stand still, as you cannot go further north.",
			"You stand still, as you cannot go further east.",
			"You stand still, as you cannot go further south.",
			"You stand still, as you cannot go further west."}
	};
	
	public Player(int startX, int startY, int speed) {
		super(startX, startY, speed);
		// TODO Auto-generated constructor stub
	}
	public String generateMap(Player player, Crocodile crocodile){
		String strReturn = "You look at your map.\n\n_|0|1|2|3|4|5|6|7|8|9|\n";
		for(int i=0;i<10;i++) {
			strReturn+=i+"|";
            for(int j=0;j<10;j++) {
            	if (player.unitMap[i][j]){
            		strReturn+="P|";
            	}else if (crocodile.unitMap[i][j]){
            		strReturn+="C|";
            	}
            	else{
            		strReturn+="_|";
            	}
            }
            strReturn+="\n";
        }
		return (strReturn);
	}
	public String getHeight(Player player) {
		int intHeight =Map.intMap[player.positionY][player.positionX];
		String strReturn = ("\nYou are now standing "+intHeight+" meters above sea level.");
		return strReturn;
	}
	public boolean checkForMaximum(Player player, int positionY, int positionX) {
		return Map.intMap[player.positionY][player.positionX]==7;
	}

}
