package crocodile_hunter;

public class Player extends Unit {

	public Player(int startX, int startY, int speed) {
		super(startX, startY, speed);
		// TODO Auto-generated constructor stub
	}
	public String generateMap(){
		String strReturn = "You look at your map.\n\n_|0|1|2|3|4|5|6|7|8|9|\n";
		for(int i=0;i<10;i++) {
			strReturn+=i+"|";
            for(int j=0;j<10;j++) {
            	if (player.unitMap[i][j]){
            		strReturn+="X|";
            	}
            	else{
            		strReturn+="_|";
            	}
            }
            strReturn+="\n";
        }
		return (strReturn);
	}

}
