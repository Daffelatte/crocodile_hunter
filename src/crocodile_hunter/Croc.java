package crocodile_hunter;

public class Croc extends Unit{

	public String[][] strAr2Move={
			{"The crocodile walks north.","The crocodile walks east","The crocodile walks south","The crocodile walks west"},
			{"The crocodile stands still, as the crocodile cannot go further north.",
			"The crocodile stands still, as the crocodile cannot go further east.",
			"The crocodile stands still, as the crocodile cannot go further south.",
			"The crocodile stands still, as the crocodile cannot go further west."}
	};
	
	public String[] strAr2Distance={
		"The crocodile ate you!",
		"The crocodile is right next to you!",
		"The crocodile is very close!",
		"The crocodile is close!",
		"The crocodile is nowhere to be seen..."
	};
	
	boolean hunt;
	public int intRage;
	
	
	public Croc(int startY, int startX, String name, int speed, int intHealth, int intMaxHealth, int damage, int[][] intAr2AttackData, String[][] strAr2AttackData,int intRunningSpeed, int intDetection) {
		super(startY,startX,name,speed,intHealth,intMaxHealth,damage,intAr2AttackData,strAr2AttackData,intRunningSpeed,intDetection);
		this.hunt=false;
	}

	public int chooseCommand(Player player, Croc croc) {
		int intReturn=0;
		int intCommand=0;
		if (hunt || main.alwaysHunt){
			intCommand=7;
		}else if (!hunt){
			intCommand=(int) Math.floor(Math.random() * 4);
			//System.out.println("intCommand = "+intCommand);
		}
		intReturn=main.intAr1Command[intCommand];
		return intReturn;
	}

	public String checkForPlayer(Player player, Croc croc) {
		String strReturn=null;
		hunt=false;
		int distanceY = Math.abs((croc.positionY - player.positionY));
		int distanceX = Math.abs((croc.positionX - player.positionX));
		int distance = Math.max(distanceY, distanceX);
		if (distance>4){
			distance=4;
		}
		if (distance==0){
			//player.executeCommand(100, player, player, croc);
			
			// change game state
			main.booAr1Gamestate[0]=false;
			main.booAr1Gamestate[1]=true;
		}else if (distance<=3){
			hunt=true;
		}
		if (hunt){
			croc.speed=1;
		} else if (!hunt){
			croc.speed=2;
		}
		croc.toggleRun();
		strReturn=strAr2Distance[distance];
		return strReturn;
	};
	
	public String hunt(Player player, Croc croc){
		int distanceY = (croc.positionY - player.positionY);
		int distanceX = (croc.positionX - player.positionX);
		String strDirection="The crocodile prowls ";
		
		if (distanceY>0){
			//n
			strDirection+="north";
			croc.executeCommand(110, croc, player, croc);
			
		}else if (distanceY<0){
			//s
			strDirection+="south";
			croc.executeCommand(115, croc, player, croc);
		}
		if (distanceX>0){
			// w
			strDirection+="west.";
			croc.executeCommand(119, croc, player, croc);
		}else if (distanceX<0){
			//e
			strDirection+="east.";
			croc.executeCommand(101, croc, player, croc);
		}
		return strDirection;
	};

	public int getDistanceToPlayer(Player player, Croc croc){
		int distanceY = Math.abs((croc.positionY - player.positionY));
		int distanceX = Math.abs((croc.positionX - player.positionX));
		int distance = Math.max(distanceY, distanceX);
		return distance;
	}

	public int chooseAttack(Player defender) {
		int intReturn=0;
		System.out.println();//"crocodile is choosing attack..."
		try {
		    Thread.sleep(1000);
		} catch(InterruptedException ex) {
		    Thread.currentThread().interrupt();
		}
		intReturn = (int) (Math.random() * 4);
		return (intReturn);
	}
	/*
	public String attack(Player defender, int intAttack) {
		String strReturn=null;
		System.out.println(this.strAr1Attack[intAttack]);
		strReturn=defender.defend(this, intAttack);
		return strReturn;
	}

	public String defend(Player attacker, int intAttack) {
		// TODO Auto-generated method stub
		String strReturn=null;
		System.out.println(intAttack);
		int crocDef=(int) Math.floor(Math.random()*4);
		if (crocDef<=1){
			if (intAttack==4){
				this.booDisarmed=true;
				strReturn="You hold the crocodile in the air";
			}else if (intAttack==5){
				this.booDisarmed=false;
				this.health-=2*attacker.damage;
				strReturn="The crocodile takes "+2*attacker.damage+" damage!";
			}else{
				this.booDisarmed=false;
				this.health-=attacker.damage;
				strReturn="The crocodile takes "+attacker.damage+" damage!";
			}
		}else if (crocDef>0){
			if (this.booDisarmed){
				this.booDisarmed=false;
				strReturn="The crocodile breaks free";
			}
			else if (intAttack==4){
				strReturn="The crocodile breaks free";
			}else if (!(intAttack==4)){
				strReturn="The crocodile evades the attack!";
			}
		}
		return strReturn;
	}
	*/
}
