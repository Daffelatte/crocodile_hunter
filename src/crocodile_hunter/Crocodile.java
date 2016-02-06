package crocodile_hunter;

public class Crocodile extends Unit{

	public String[][] strMoveList={
			{"The crocodile walks north.","The crocodile walks east","The crocodile walks south","The crocodile walks west"},
			{"The crocodile stands still, as the crocodile cannot go further north.",
			"The crocodile stands still, as the crocodile cannot go further east.",
			"The crocodile stands still, as the crocodile cannot go further south.",
			"The crocodile stands still, as the crocodile cannot go further west."}
	};
	
	public String[] strDistanceList={
		"The crocodile ate you!",
		"The crocodile is right next to you!",
		"The crocodile is very close!",
		"The crocodile is close!",
		"The crocodile is nowhere to be seen..."
	};
	
	boolean hunt = false;
	int speed;
	
	public Crocodile(int startX, int startY, int speed) {
		super(startX, startY, speed);
		speed=speed;
		// TODO Auto-generated constructor stub
	}

	public int chooseCommand(Player player, Crocodile crocodile) {
		int intReturn=0;
		int intCommand=0;
		if (hunt){
			int distanceY = (crocodile.positionY - player.positionY);
			int distanceX = (crocodile.positionX - player.positionX);
			
			if (distanceY>0){
				//n
				intCommand=0;
				crocodile.executeCommand(110, crocodile, player, crocodile);
			}else if (distanceY<0){
				//s
				intCommand=2;
				crocodile.executeCommand(115, crocodile, player, crocodile);
			}
			if (distanceX>0){
				// w
				intCommand=3;
			}else if (distanceX<0){
				//e
				intCommand=1;
			}
		}else if (!hunt){
			intCommand=(int) Math.floor(Math.random() * 4);
			//System.out.println("intCommand = "+intCommand);
		}
		intReturn=main.intCommandList[intCommand];
		return intReturn;
	}

	public String checkForPlayer(Player player, Crocodile crocodile) {
		String strReturn=null;
		hunt=false;
		int distanceY = Math.abs((crocodile.positionY - player.positionY));
		int distanceX = Math.abs((crocodile.positionX - player.positionX));
		int distance = Math.max(distanceY, distanceX);
		if (distance>4){
			distance=4;
		}
		if (distance==0){
			player.executeCommand(100, player, player, crocodile);
		}else if (distance<=3){
			hunt=true;
		}
		if (hunt){
			crocodile.speed=1;
		} else if (!hunt){
			crocodile.speed=2;
		}
		System.out.println("hunt="+hunt);
		strReturn=strDistanceList[distance];
		return strReturn;
	};

	
	
}
