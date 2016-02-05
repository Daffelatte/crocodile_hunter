package crocodile_hunter;
import crocodile_hunter.main;


public class Unit {
	public static int positionX = 0;
	public static int positionY = 0;
	public static int deltaX = 0;
	public static int deltaY = 0;
	public static int[][] positionDelta={
			{-0,0,0,0},
			{0,0,0,-0},
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
	public Unit(int startX, int startY, int speed){
		int positionX = startX;
		int positionY = startY;
		int deltaX = 0;
		int deltaY = 0;
		int[][] positionDelta={
				{-speed,0,speed,0},
				{0,speed,0,-speed},
		};
		unitMap[positionY][positionX]=true;
	}
	
	public static String executeCommand(int intCommand, Unit activeUnit, Player player){
		String strReturn = null;
		for (int i=0;main.intCommandList.length>i;i++){
			if (main.intCommandList[i]==intCommand){
				if (i<4){
					deltaY=positionDelta[0][i];
					deltaX=positionDelta[1][i];
					strReturn=activeUnit.walk(deltaY,deltaX,i);
				}else if (i==4){
					strReturn=player.generateMap();
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
	
	String walk(int deltaY, int deltaX, int i){
		String strReturn = null;
		if (positionY+deltaY<10 && positionY+deltaY>-1 && positionX+deltaX<10 && positionX+deltaX>-1){
			unitMap[positionY][positionX]=false;
			positionY+=deltaY;
			positionX+=deltaX;
			unitMap[positionY][positionX]=true;
			strReturn=main.strMoveList[0][i];
		}else{
			strReturn=main.strMoveList[1][i];
		};
		return (strReturn);
	};

}
