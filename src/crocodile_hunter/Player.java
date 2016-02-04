package crocodile_hunter;
import crocodile_hunter.main;

public class Player {
	static int positionX = 0;
	static int positionY = 9;
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
	}

}
