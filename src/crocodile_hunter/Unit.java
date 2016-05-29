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
	public boolean isActiveUnit;
	
	public String name;
	public int speed;
	public int runningSpeed;
	public int health;
	public int maxHealth;
	public int damage;

	public int strength = 0;
	public int weakness = 0;
	public int protection = 0;
	public int vulnerability = 0;
	public int evasion = 0;
	public int blindness = 0;
	public int restoration = 0;
	public int suffering = 0;
	
	public int strengthDuration = 0;
	public int weaknessDuration = 0;
	public int protectionDuration = 0;
	public int vulnerabilityDuration = 0;
	public int evasionDuration = 0;
	public int blindnessDuration = 0;
	public int restorationDuration = 0;
	public int sufferingDuration = 0;

	public int[][] intAr2AttackData;
	public String[][] strAr2AttackData;
	
	boolean isRunning;
	
	public int[] intAr1BuffLevel={strength, weakness, protection, vulnerability, evasion, blindness, restoration, suffering};
	public int[] intAr1BuffDuration={strengthDuration, weaknessDuration, protectionDuration, vulnerabilityDuration, evasionDuration, blindnessDuration, restorationDuration, sufferingDuration};
	
	public int[][] positionDelta={
			{-1,0,1,0},
			{0,1,0,-1},
	};
	static boolean[][] booAr2unitMap = new boolean[Map.intMapSize][Map.intMapSize];
	
	/*public boolean[][] booAr2unitMap = {
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
	};*/
	
	public String[][] strAr2Move={
			{"You walk north.","You walk east","You walk south","You walk west"},
			{"You stand still, as you cannot go further north.",
			"You stand still, as you cannot go further east.",
			"You stand still, as you cannot go further south.",
			"You stand still, as you cannot go further west."}
	};
	
	public Unit(int startY, int startX, String name, int speed, int intHealth, int intMaxHealth, int damage, int[][] intAr2AttackData, String[][] strAr2AttackData, int intRunningSpeed, int intDetection){
		
		System.out.println(main.intDifficulty);
		this.health = intHealth;
		this.maxHealth = intMaxHealth;
		this.speed = speed;
		this.runningSpeed = intRunningSpeed;
		this.damage = damage;
		this.intAr2AttackData = intAr2AttackData;
		this.strAr2AttackData = strAr2AttackData;
		this.name = name;
		this.isActiveUnit=true;
		
		positionX = startX;
		positionY = startY;
		deltaX = 0;
		deltaY = 0;
		positionDelta[0][0]=-speed;
		positionDelta[1][1]=speed;
		positionDelta[0][2]=speed;
		positionDelta[1][3]=-speed;
		
		for (int y=0;y<Map.intMapSize;y++){
			for (int x=0;x<Map.intMapSize;x++){
				booAr2unitMap[y][x]=false;
			}
		}
		
		booAr2unitMap[positionY][positionX]=true;
	}
	
	public String executeCommand(int intCommand, Unit activeUnit, Player player, Croc croc){
		String strReturn = null;
		for (int i=0;main.intAr1Command.length>i;i++){
			if (main.intAr1Command[i]==intCommand){
				if (i<4){
					deltaY=activeUnit.positionDelta[0][i];
					deltaX=activeUnit.positionDelta[1][i];
					this.isActiveUnit=false;
					strReturn=activeUnit.walk(deltaY,deltaX,i,activeUnit,player,croc);
				}else if (i==4){
					strReturn=Map.generateStrMap(player, croc, Map.intAr2ActiveMap, Map.strAr1ActiveASCII);
					this.isActiveUnit=false;
				}else if (i==5){
					strReturn=main.strCommands;
				}else if(i==6){
					strReturn="You embrace death.";
					main.booIsRunning=false;
				}else if (i==7){
					strReturn=croc.hunt(player, croc);
				}else if (i==8){
					strReturn=player.toggleRun();
				};
				//strReturn=main.strCommandList[i];
				break;
			}
		}
		return strReturn;
	}
	
	String walk(int deltaY, int deltaX, int i, Unit activeUnit, Player player, Croc croc){
		String strReturn = null;
		if (activeUnit.positionY+deltaY<Map.intMapSize && activeUnit.positionY+deltaY>-1 && activeUnit.positionX+deltaX<Map.intMapSize && activeUnit.positionX+deltaX>-1){
			booAr2unitMap[activeUnit.positionY][activeUnit.positionX]=false;
			activeUnit.positionY+=deltaY;
			activeUnit.positionX+=deltaX;
			booAr2unitMap[activeUnit.positionY][activeUnit.positionX]=true;
			if (activeUnit instanceof Player) {
				if (player.isRunning){
					player.stamina-=2;
					strReturn=player.strAr2Move[2][i];
				}else{
					strReturn=player.strAr2Move[0][i];
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
	public String toggleRun(){
		String strReturn=null;
		int newSpeed;
		this.isRunning=!this.isRunning;
		if (this.isRunning){
			newSpeed=this.runningSpeed;
			strReturn="You start running";
		}else{
			newSpeed=this.speed;
			strReturn="You stop running";
		}
		this.positionDelta[0][0]=-newSpeed;
		this.positionDelta[1][1]=newSpeed;
		this.positionDelta[0][2]=newSpeed;
		this.positionDelta[1][3]=-newSpeed;
		return strReturn;
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
			Map.intNumOfPeaks=(int) Math.floor((Map.intMapSize*Map.intMapSize)/33);
			
		
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
	public Boolean validateAttack(int intAttack){
		boolean booReturn;
		booReturn=true;
		return booReturn;
	};
	public void attack(Unit defender, int intAttack) {
		System.out.println(this.strAr2AttackData[1][intAttack]);
		if(this.isAttackBuffed(intAttack) && this.isAttackSelfBuff(intAttack)){
			System.out.println("self buff");
			this.applyBuff(this.intAr2AttackData[intAttack][3], this.intAr2AttackData[intAttack][4], this.intAr2AttackData[intAttack][5]);
		}
		int intHit=(int) Math.floor(Math.random()*5);
		if (intHit>=this.blindness){
			defender.defend(this, intAttack);
		}else{
			System.out.println("miss!");
		}
		return;
	};
	public void defend(Unit attacker, int intAttack) {
		// TODO Auto-generated method stub
		int effectiveDamage;
		//check for success
		int intHit=(int) Math.floor(Math.random()*5);
		if (intHit>=this.evasion){
			//apply buffs
			System.out.println("buff?="+this.isAttackBuffed(intAttack));
			System.out.println("!selfbuff?=");
			if(this.isAttackBuffed(intAttack) && !(this.isAttackSelfBuff(intAttack))){
				System.out.println(!(this.isAttackSelfBuff(intAttack)));
				this.applyBuff(this.intAr2AttackData[intAttack][3], this.intAr2AttackData[intAttack][4], this.intAr2AttackData[intAttack][5]);
			}
			//deal damage
			effectiveDamage=(attacker.damage+attacker.intAr2AttackData[intAttack][0]+attacker.strength-attacker.weakness+this.vulnerability-this.protection);
			if (effectiveDamage<0){
				effectiveDamage=0;
			}
			this.health-=effectiveDamage;
			System.out.println(this.name+" takes "+effectiveDamage+" damage");
		}else{
			System.out.println("miss!");
		}
		
		
		return;
	}
	public boolean isAttackBuffed(int intAttack){
		/*
		System.out.println("hello");
		System.out.println(this.intAr2AttackData[4][0]);
		System.out.println(this.intAr2AttackData[4][1]);
		System.out.println("intAttack="+intAttack);
		System.out.println(1==this.intAr2AttackData[intAttack][1]);
		*/
		return (1==this.intAr2AttackData[intAttack][1]);
	}
	public boolean isAttackSelfBuff(int intAttack){
		return (0==this.intAr2AttackData[intAttack][2]);
	}
	public void applyBuff(int intBuffType, int intBuffLevel, int intBuffDuration){
		this.intAr1BuffLevel[intBuffType]=intBuffLevel;
		this.intAr1BuffDuration[intBuffType]=intBuffDuration;
		
		strength = intAr1BuffLevel[0];
		weakness = intAr1BuffLevel[1];
		protection = intAr1BuffLevel[2];
		vulnerability = intAr1BuffLevel[3];
		evasion = intAr1BuffLevel[4];
		blindness = intAr1BuffLevel[5];
		restoration = intAr1BuffLevel[6];
		suffering = intAr1BuffLevel[7];
		
		strengthDuration = intAr1BuffDuration[0];
		weaknessDuration = intAr1BuffDuration[1];
		protectionDuration = intAr1BuffDuration[2];
		vulnerabilityDuration = intAr1BuffDuration[3];
		evasionDuration = intAr1BuffDuration[4];
		blindnessDuration = intAr1BuffDuration[5];
		restorationDuration = intAr1BuffDuration[6];
		sufferingDuration = intAr1BuffDuration[7];
		
		System.out.println(this.name+" gains "+Buff.strAr2BuffData[intBuffType][0]+" "+intBuffLevel);
	}
	public void updateBuffs(){
		// heal 
		if (this.restorationDuration>=1){
			this.health+=this.restoration;
			System.out.println(this.name+" is healed for "+this.restoration+" by restoration "+this.restoration);
		}
		// suffer
		if (this.sufferingDuration>=1){
			this.health-=this.suffering;
			System.out.println(this.name+" takes "+this.suffering+" damage by suffering "+this.suffering);
		}
		for (int i=0;i<intAr1BuffDuration.length;i++){
			if (intAr1BuffDuration[i]>0){
				intAr1BuffDuration[i]--;
				if (intAr1BuffDuration[i]==0){
					intAr1BuffLevel[i]=0;
					System.out.println(this.name+" looses "+Buff.strAr2BuffData[i][0]);
				}
			}
			
		}
	}
	public void checkForIllegalHealth(){
		if (this.health>this.maxHealth){
			this.health=this.maxHealth;
			System.out.println(this.name+" health can't exeed "+this.maxHealth);
		}
	}
}
