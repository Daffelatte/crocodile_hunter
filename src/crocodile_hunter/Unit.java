package crocodile_hunter;
import crocodile_hunter.main;


public class Unit {
	public int positionX = 0;
	public int positionY = 9;
	public int deltaX = 0;
	public int deltaY = 0;
	public int speed = 1;
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
	
	public Unit(int startY, int startX, int speed){
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
					strReturn=Map.generateStrMap(player, crocodile, Map.generateIntMap());
				}else if (i==5){
					strReturn=main.strCommands;
				}else if(i==6){
					strReturn="You embrace death.";
					main.booIsRunning=false;
				}else if (i==7){
					strReturn=crocodile.hunt(player, crocodile);
				}else if (i==8){
					strReturn=player.toggleRun(player);
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
				if (player.speed==1){
					strReturn=player.strMoveList[0][i];
				}else if (player.speed==4){
					strReturn=player.strMoveList[2][i];
					player.exhasted=true;
				}
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
	
	public void changeSpeed(Unit activeUnit){
		activeUnit.positionDelta[0][0]=-activeUnit.speed;
		activeUnit.positionDelta[1][1]=activeUnit.speed;
		activeUnit.positionDelta[0][2]=activeUnit.speed;
		activeUnit.positionDelta[1][3]=-activeUnit.speed;
	}

	public static boolean isCommandValid(int intPlayerCommand) {
		boolean booReturn=false;
		for (int i=0;main.intCommandList.length>i;i++){
			if (main.intCommandList[i]==intPlayerCommand){
				/*if (!(i==4 && !main.allowMap)){
					return true;
				}*/
				booReturn=(!(i==4 && !main.allowMap));
			}
		}
		return booReturn;
	}

	public static void setDifficulty(int intDifficulty) {
		// TODO Auto-generated method stub
			main.alwaysGenerateMap=main.booDifficultyList[0];
			main.showHeightOnMap=main.booDifficultyList[0];
			main.showCrocodileOnMap=main.booDifficultyList[0];
			main.allowMap=main.booDifficultyList[1];
			if (main.booDifficultyList[1]){
				main.strCommands+="m = Open map\n";
				main.strValidCommands+=", m";
			};
			main.alwaysHunt=main.booDifficultyList[3];
			Map.intActiveMap=Map.intRandomMap;
			
		
	}
	

}
