package crocodile_hunter;
import crocodile_hunter.main;


public class Unit {
	public int positionX = 0;
	public int positionY = 9;
	public int deltaX = 0;
	public int deltaY = 0;
	public int[][] positionDelta={
			{-1,0,1,0},
			{0,1,0,-1},
	};
	public boolean[][] unitMap = {
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
	};
	
	public String[][] strMoveList={
			{"You walk north.","You walk east","You walk south","You walk west"},
			{"You stand still, as you cannot go further north.",
			"You stand still, as you cannot go further east.",
			"You stand still, as you cannot go further south.",
			"You stand still, as you cannot go further west."}
	};
	
	public Unit(int startX, int startY, int speed){
		speed = speed;
		positionX = startX;
		positionY = startY;
		deltaX = 0;
		deltaY = 0;
		positionDelta[0][0]=-speed;
		positionDelta[1][1]=speed;
		positionDelta[0][2]=speed;
		positionDelta[1][3]=-speed;
		unitMap[positionY][positionX]=true;
	}
	
	public String executeCommand(int intCommand, Unit activeUnit, Player player, Crocodile crocodile){
		String strReturn = null;
		for (int i=0;main.intCommandList.length>i;i++){
			if (main.intCommandList[i]==intCommand){
				if (i<4){
					deltaY=activeUnit.positionDelta[0][i];
					deltaX=activeUnit.positionDelta[1][i];
					strReturn=activeUnit.walk(deltaY,deltaX,i,activeUnit,player,crocodile);
				}else if (i==4){
					strReturn=player.generateMap(player, crocodile);
				}else if (i==5){
					strReturn=main.strCommands;
				}else if(i==6){
					strReturn="You embrace death.";
					main.isRunning=40;
				};
				//strReturn=main.strCommandList[i];
				break;
			}
		}
		
		return strReturn;
	}
	
	String walk(int deltaY, int deltaX, int i, Unit activeUnit, Player player, Crocodile crocodile){
		String strReturn = null;
		if (activeUnit.positionY+deltaY<10 && activeUnit.positionY+deltaY>-1 && activeUnit.positionX+deltaX<10 && activeUnit.positionX+deltaX>-1){
			unitMap[activeUnit.positionY][activeUnit.positionX]=false;
			activeUnit.positionY+=deltaY;
			activeUnit.positionX+=deltaX;
			unitMap[activeUnit.positionY][activeUnit.positionX]=true;
			if (activeUnit instanceof Player) {
				strReturn=player.strMoveList[0][i];
			}else if (activeUnit instanceof Crocodile){
				strReturn=crocodile.strMoveList[0][i];
			}
		}else{
			if (activeUnit instanceof Player) {
				strReturn=player.strMoveList[1][i];
			}else if (activeUnit instanceof Crocodile){
				strReturn=crocodile.strMoveList[1][i];
			}
		};
		return (strReturn);
	};

}
