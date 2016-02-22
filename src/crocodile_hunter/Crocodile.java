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
	
	public String[] strAttackList={"mid-headbutt", "high-tail whip", "low-spinning-tail-sweep", "bite ()", "ravage ()"};
	public String[] strAttackDescriptionList={"slam your head into your opponent", "A spinning tail kick", "A quick legsweep", "Bite and hold your opponent in your jaws", "Violently shake, tearing appart your opponent"};
	
	public Crocodile(int startX, int startY, int speed, int health) {
		super(startX, startY, speed, health);
		speed=speed;
	}

	public int chooseCommand(Player player, Crocodile crocodile) {
		int intReturn=0;
		int intCommand=0;
		if (hunt || main.alwaysHunt){
			intCommand=7;
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
			//player.executeCommand(100, player, player, crocodile);
			
			// change game state
			main.booGamestateList[0]=false;
			main.booGamestateList[1]=true;
		}else if (distance<=3){
			hunt=true;
		}
		if (hunt){
			crocodile.speed=1;
		} else if (!hunt){
			crocodile.speed=2;
		}
		crocodile.changeSpeed(crocodile);
		strReturn=strDistanceList[distance];
		return strReturn;
	};
	
	public String hunt(Player player, Crocodile crocodile){
		int distanceY = (crocodile.positionY - player.positionY);
		int distanceX = (crocodile.positionX - player.positionX);
		String strDirection="The crocodile prowls ";
		
		if (distanceY>0){
			//n
			strDirection+="north";
			crocodile.executeCommand(110, crocodile, player, crocodile);
			
		}else if (distanceY<0){
			//s
			strDirection+="south";
			crocodile.executeCommand(115, crocodile, player, crocodile);
		}
		if (distanceX>0){
			// w
			strDirection+="west.";
			crocodile.executeCommand(119, crocodile, player, crocodile);
		}else if (distanceX<0){
			//e
			strDirection+="east.";
			crocodile.executeCommand(101, crocodile, player, crocodile);
		}
		return strDirection;
	};

	public int getDistanceToPlayer(Player player, Crocodile crocodile){
		int distanceY = Math.abs((crocodile.positionY - player.positionY));
		int distanceX = Math.abs((crocodile.positionX - player.positionX));
		int distance = Math.max(distanceY, distanceX);
		return distance;
	}

	public int chooseAttack() {
		int intReturn = (int) Math.floor(Math.random() * 4);
		return (intReturn);
	}

}
