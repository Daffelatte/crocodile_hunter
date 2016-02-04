package crocodile_hunter;
import crocodile_hunter.main;


public class Player {
	static int positionX = 0;
	static int positionY = 9;
	static int deltaX = 0;
	static int deltaY = 0;
	
	static boolean[][] playerMap = {
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{false,false,false,false,false,false,false,false,false,false},
			{true,false,false,false,false,false,false,false,false,false},
	};
	public Player(){
	}
	static String executeCommand(int intCommand){
		String strReturn = null;
		for (int i=0;main.intCommandList.length>i;i++){
			if (main.intCommandList[i]==intCommand){
				if (i<4){
					deltaY=main.positionDelta[0][i];
					deltaX=main.positionDelta[1][i];
					strReturn=Player.walk(deltaY,deltaX,i);
				}else if (i==4){
					strReturn=Player.generateMap();
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
	static String walk(int deltaY, int deltaX, int i){
		String strReturn = null;
		if (positionY+deltaY!=10 && positionY+deltaY!=-1 && positionX+deltaX!=10 && positionX+deltaX!=-1){
			playerMap[positionY][positionX]=false;
			positionY+=deltaY;
			positionX+=deltaX;
			playerMap[positionY][positionX]=true;
			strReturn=main.strMoveList[0][i];
		}else{
			strReturn=main.strMoveList[1][i];
		};
		return (strReturn);
	};
	static String generateMap(){
		String strReturn = "You look at your map.\n\n_|0|1|2|3|4|5|6|7|8|9|\n";
		for(int i=0;i<10;i++) {
			strReturn+=i+"|";
            for(int j=0;j<10;j++) {
            	if (Player.playerMap[i][j])
            		strReturn+="X|";
            	else{
            		strReturn+="_|";
            	}
            }
            strReturn+="\n";
        }
		return (strReturn);
	};
	/*
	static String walk(int direction){
		String strDirection="[error: direction 0]";
		switch(direction){
			//north
			case 110:
				if (positionY != 0){
					playerMap[positionY][positionX]=false;
					positionY--;
					playerMap[positionY][positionX]=true;
					strDirection = "You walk north.";
				}else{
					strDirection = "You stand still, as you cannot go further north.";
				}
				break;
			//east
			case 101:
				if (positionX != 9){
					playerMap[positionY][positionX]=false;
					positionX++;
					playerMap[positionY][positionX]=true;
					strDirection = "You walk east.";
				}else{
					strDirection = "You stand still, as you cannot go further east.";
				}
				break;
			//south
			case 115:
				if (positionY != 9){
					playerMap[positionY][positionX]=false;
					positionY++;
					playerMap[positionY][positionX]=true;
					strDirection = "You walk south.";
				}else{
					strDirection = "You stand still, as you cannot go further south.";
				}
				break;
			//west
			case 119:
				if (positionX != 0){
					playerMap[positionY][positionX]=false;
					positionX--;
					playerMap[positionY][positionX]=true;
					strDirection = "You walk west.";
				}else{
					strDirection = "You stand still, as you cannot go further west.";
				}
				break;
			default:
				strDirection = "Invalid command, you stand still.";
				break;
		}
		return (strDirection);
	}*/

}
