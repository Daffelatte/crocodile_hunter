package crocodile_hunter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import crocodile_hunter.main;


public class Unit {
	public int positionX = 0;
	public int positionY = 9;
	public int deltaX = 0;
	public int deltaY = 0;
	
	public int speed;
	public int health;
	public int damage;
	public String[] strAr1Attack;
	public String[] strAr1AttackText;
	public boolean booRestrained = false;
	
	public int[][] positionDelta={
			{-1,0,1,0},
			{0,1,0,-1},
	};
	public boolean[][] booAr2unitMap = {
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
	
	public String[][] strAr2Move={
			{"You walk north.","You walk east","You walk south","You walk west"},
			{"You stand still, as you cannot go further north.",
			"You stand still, as you cannot go further east.",
			"You stand still, as you cannot go further south.",
			"You stand still, as you cannot go further west."}
	};

	
	public Unit(int startY, int startX, int speed, int[] intAr1Health, int damage, String[] strAr1Attack, String[] strAr1AttackText){
		
		System.out.println(main.intDifficulty);
		this.health = intAr1Health[main.intDifficulty];
		this.speed = speed;
		this.damage = damage;
		this.strAr1Attack = strAr1Attack;
		this.strAr1AttackText = strAr1AttackText;
		
		positionX = startX;
		positionY = startY;
		deltaX = 0;
		deltaY = 0;
		positionDelta[0][0]=-speed;
		positionDelta[1][1]=speed;
		positionDelta[0][2]=speed;
		positionDelta[1][3]=-speed;
		booAr2unitMap[positionY][positionX]=true;
	}
	
	public String executeCommand(int intCommand, Unit activeUnit, Player player, Croc croc){
		String strReturn = null;
		for (int i=0;main.intAr1Command.length>i;i++){
			if (main.intAr1Command[i]==intCommand){
				if (i<4){
					deltaY=activeUnit.positionDelta[0][i];
					deltaX=activeUnit.positionDelta[1][i];
					strReturn=activeUnit.walk(deltaY,deltaX,i,activeUnit,player,croc);
				}else if (i==4){
					strReturn=Map.generateStrMap(player, croc, Map.intAr2ActiveMap, Map.strAr1ActiveASCII);
				}else if (i==5){
					strReturn=main.strCommands;
				}else if(i==6){
					strReturn="You embrace death.";
					main.booIsRunning=false;
				}else if (i==7){
					strReturn=croc.hunt(player, croc);
				}else if (i==8){
					strReturn=player.toggleRun(player);
				};
				//strReturn=main.strCommandList[i];
				break;
			}
		}
		
		return strReturn;
	}
	
	String walk(int deltaY, int deltaX, int i, Unit activeUnit, Player player, Croc croc){
		String strReturn = null;
		if (activeUnit.positionY+deltaY<10 && activeUnit.positionY+deltaY>-1 && activeUnit.positionX+deltaX<10 && activeUnit.positionX+deltaX>-1){
			booAr2unitMap[activeUnit.positionY][activeUnit.positionX]=false;
			activeUnit.positionY+=deltaY;
			activeUnit.positionX+=deltaX;
			booAr2unitMap[activeUnit.positionY][activeUnit.positionX]=true;
			if (activeUnit instanceof Player) {
				if (player.speed==1){
					strReturn=player.strAr2Move[0][i];
				}else if (player.speed==4){
					strReturn=player.strAr2Move[2][i];
					player.exhasted=true;
				}
			}else if (activeUnit instanceof Croc){
				strReturn=croc.strAr2Move[0][i];
			}
		}else{
			if (activeUnit instanceof Player) {
				strReturn=player.strAr2Move[1][i];
			}else if (activeUnit instanceof Croc){
				strReturn=croc.strAr2Move[1][i];
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
		for (int i=0;main.intAr1Command.length>i;i++){
			if (main.intAr1Command[i]==intPlayerCommand){
				/*if (!(i==4 && !main.allowMap)){
					return true;
				}*/
				booReturn=(!(i==4 && !main.allowMap));
			}
		}
		return booReturn;
	};

	public static void setDifficulty(int intDifficulty) {
		// TODO Auto-generated method stub
			main.intDifficulty=intDifficulty;
			
			main.alwaysGenerateMap=main.booAr1Difficulty[0];
			main.showHeightOnMap=main.booAr1Difficulty[0];
			main.showCrocOnMap=main.booAr1Difficulty[0];
			main.allowMap=main.booAr1Difficulty[1];
			if (main.booAr1Difficulty[1]){
				main.strCommands+="m = Open map\n";
				main.strValidCommands+=", m";
			};
			main.alwaysHunt=main.booAr1Difficulty[3];
			
			
			// independent
			Map.intAr2ActiveMap=Map.intAr2RandomMap;
			Map.intNumOfPeaks=3;
			
		
	};
	
	public void reLocate(Unit activeUnit, Player player, Croc croc){
		int[][] intValidLocations = new int[2][80];
		int i = 0;
		int max = 0;
		// find the highest value in the map (lowest poing in mountain)
		if (activeUnit==player){
			for (int a=0;a<10;a++){
				for (int b=0;b<10;b++){
					if (Map.intAr2RandomMap[a][b]>max){
						max=Map.intAr2RandomMap[a][b];
					};
				};
			};
		};
		// Find valid locations
		for (int y=0;y<10;y++){
			for (int x=0;x<10;x++){
				if (activeUnit==player && Map.intAr2RandomMap[y][x]==max){
					intValidLocations[0][i] = y;
					intValidLocations[1][i] = x;
					//System.out.println(y+","+x+" is valid");
					i++;
				}else{
					//System.out.println(y+","+x+" is not valid");
				}
				if(activeUnit==croc){
					booAr2unitMap[croc.positionY][croc.positionX]=false;
					croc.positionY=y;
					croc.positionX=x;
					booAr2unitMap[croc.positionY][croc.positionX]=true;
					if (croc.getDistanceToPlayer(player, croc)>4 && croc.getDistanceToPlayer(player, croc)<7){
						intValidLocations[0][i] = y;
						intValidLocations[1][i] = x;
					};
				};
			};
		};
		// Place unit in a randomly selected valid location
		int intRandomI = (int) Math.floor(Math.random()*(i));
		int intValidY = intValidLocations[0][intRandomI];
		int intValidX = intValidLocations[1][intRandomI];
		booAr2unitMap[activeUnit.positionY][activeUnit.positionX]=false;
		activeUnit.positionY=intValidY;
		activeUnit.positionX=intValidX;
		booAr2unitMap[activeUnit.positionY][activeUnit.positionX]=true;
		//System.out.println(Arrays.deepToString(intValidLocations));
	};

}
