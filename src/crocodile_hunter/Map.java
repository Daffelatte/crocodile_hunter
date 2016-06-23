package crocodile_hunter;

import java.lang.reflect.Array;

public class Map{

	static int[][] intAr2DefaultMap = {
			{2,1,0,0,1,2,3,3,4,4},
			{3,2,1,1,2,2,3,4,5,5},
			{4,4,2,2,3,3,4,5,6,6},
			{5,5,4,4,4,4,5,6,7,6},
			{6,5,5,4,3,3,4,5,5,6},
			{5,4,3,3,2,1,2,3,3,4},
			{3,3,2,2,1,0,1,2,3,3},
			{3,2,1,1,1,1,1,3,4,5},
			{1,1,0,0,0,1,2,3,4,6},
			{0,0,1,1,1,2,3,3,5,6}
	};
	static int intMapSize=12;
	
	static int[][] intAr2RandomMap = new int[intMapSize][intMapSize];
	/*static int[][] intAr2RandomMap = {
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0}

	};*/
	static int[][] intAr2EventMap = new int[intMapSize][intMapSize];
	/*static int[][] intAr2EventMap = {

			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
	};*/
	static int[][] intAr2WeaponMap = new int[intMapSize][intMapSize];
	/*static int[][] intAr2WeaponMap = {
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
			{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1}
	};*/
	static int[] intAr1Height = {4,4,3,3,2,2,1,1,0,0};
	static String[] strAr1FullASCII = {" ","@","¤","+","=","~","-",",","."," "," "," "," "," "," "};
	//static String[] strSemiASCIIList = {" ","~","~","~","~","-","-","-","-","-"};
	static String[] strAr1SemiASCII = {" ","@","-","-","."," "," "," "," "," "," "," "," "," "," "};
	//static String[] strASCIIList = {"0","1","2","3","4","5","6","7","8","9","A","B"};
	static String[] strAr1MapCoordinateChars = {
				"0","1","2","3","4","5","6","7","8","9",
				"A","B","C","D","E","F","G","H","I","J",
				"K","L","M","N","O","P","Q","R","S","T",
				"U","V","W","X","Y","Z"
		};
	static String[] strAr1ActiveASCII=strAr1SemiASCII;
	static int intNumOfPeaks=1;
	public static int[][] intAr2ActiveMap=intAr2RandomMap;
	
	static int[] intAr1EventType = {0,0,1};
	static int[] intAr1EventColor = {0,1,2,3};
	static int[] intAr1EventOdd = {0,0,0,1};

	// buff, level, duration, deBuff, level, duration
	static int[][] intAr2EventData = {
			{0,1,6,1,1,6},//red berries
			{2,1,6,3,1,6},//blue berries
			{4,1,6,5,1,6},//green berries
			{6,1,3,7,1,3},//yellow berries
			
			{0,2,6,1,2,6},//red frog
			{2,2,6,3,2,6},//blue frog
			{4,2,6,5,2,6},//green frog
			{6,2,3,7,2,3},//yellow frog
	};
	

	public static String[][] strAr2EventData = {
			{"You find a bush with red berries, do you eat them? (Y/N)", "You eat the red berries...", "You ignore them."},//red berries
			{"You find a bush with blue berries, do you eat them? (Y/N)", "You eat the blue berries...", "You ignore them."},//blue berries
			{"You find a bush with green berries, do you eat them? (Y/N)", "You eat the green berries...", "You ignore them."},//green berries
			{"You find a bush with yellow berries, do you eat them? (Y/N)", "You eat the yellow berries...", "You ignore them."},//yellow berries
			
			{"You find a red frog, do you lick it? (Y/N)", "You lick the red frog...", "You ignore it."},//red frog
			{"You find a blue frog, do you lick it? (Y/N)", "You lick the blue frog...", "You ignore it."},//blue frog
			{"You find a green frog, do you lick it? (Y/N)", "You lick the green frog...", "You ignore it."},//green frog
			{"You find a yellow frog, do you lick it? (Y/N)", "You lick the yellow frog...", "You ignore it."},//yellow frog
	};
	static int[][] intAr2WeaponAttackData = {
			//damage, hasBuff?(0=false, 1=true), target(0=self, 1=enemy), buffType, buffLevel, buffDuration
			{-2,1,1,7,1,4}, // flint
			{-2,1,1,7,2,1}, // sharp rock
			{0,1,1,1,1,1}, // fist sized stone
			{1,0}, // bone
			
			{-8,1,0,6,1,1}, // Ash
			{0,1,1,5,2,1}, // Branch
			{-8,1,1,1,1,4}, // flute
			{-8,1,0,0,1,4}, // mud
			
			{-1,1,1,5,1,6}, // soil
			{-2,1,0,2,2,2}, // bark
			{-1,1,0,6,1,2} // stinger
	};
	static String[][] strAr2WeaponAttackData = {
			{"attackName", "attackTextFlint"}, // flint
			{"attackName", "attackTextRock"}, // sharp rock
			{"attackName", "attackTextStone"}, // fist sized stone
			{"attackName", "attackTextBone"}, // bone
			
			{"attackName", "attackTextAsh"}, // Ash
			{"attackName", "attackTextBranch"}, // Branch
			{"attackName", "attackTextFlute"}, // flute
			{"attackName", "attackTextMud"}, // mud
			
			{"attackName", "attackTextSoil"}, // soil
			{"attackName", "attackTextBark"}, // bark
			{"attackName", "attackTextStinger"} // stinger
	};
	public static String[][] strAr2WeaponData = {
			// name,find, drop, y, n
			{"Flint","You find a piece of flint, do you pick it up? (Y/N)"},
			{"Sharp rock","You find a sharp rock, do you pick it up? (Y/N)"},
			{"Fist-sized stone","You find a fist-sized stone, do you pick it up? (Y/N)"},
			{"Bone","You find a large bone, do you pick it up? (Y/N)"},
			
			{"Ash","You find some ash, do you pick it up? (Y/N)"},
			{"Staff-like branch","You find a sturdy, staff-like branch, do you pick it up? (Y/N)"},
			{"Unsettling flute","You find a creepy, unsettling flute, do you pick it up? (Y/N)"},
			{"Mud","You find some mud that would work as war paint, do you pick it up? (Y/N)"},
			
			{"Dry soil","You find some dry soil, do you pick it up? (Y/N)"},
			{"Protective Bark","You find a piece of bark, big enough to use as a shield. Do you pick it up? (Y/N)"},
			{"Stinger","You find a large stinger, dripping from it's point. Do you pick it up? (Y/N)"}
	};
	Map(){
	}
	public static String generateStrMap(Player player, Croc croc, int[][] intActiveMap, String[] strAr1ActiveASCII){
		String strReturn = "You look at your map.\n\n"
				+ "_|";
		for (int i=0;i<intMapSize;i++){ //+ "_|0|1|2|3|4|5|6|7|8|9|"
			strReturn+=strAr1MapCoordinateChars[i]+"|";
		}
		strReturn+="  _|";
		for (int i=0;i<intMapSize;i++){ //+ "_|0|1|2|3|4|5|6|7|8|9|"
			strReturn+=strAr1MapCoordinateChars[i]+"|";
		}
		strReturn+="  _|";
		for (int i=0;i<intMapSize;i++){ //+ "_|0|1|2|3|4|5|6|7|8|9|"
			strReturn+=strAr1MapCoordinateChars[i]+"|";
		}
				
		strReturn+="\n";
		for(int i=0;i<intMapSize;i++) {
			strReturn+=strAr1MapCoordinateChars[i]+"|";
            for(int j=0;j<intMapSize;j++) {
            	if (player.booAr2unitMap[i][j]){
            		strReturn+="P|";
            	}else if (croc.booAr2unitMap[i][j] && main.showCrocOnMap){
            		strReturn+="C|";
            	}else{
            		strReturn+=strAr1ActiveASCII[Map.intAr2ActiveMap[i][j]]+"|";
            	}
            	/*else if (main.showHeightOnMap && (Map.intActiveMap[i][j]>4)){
            		strReturn+="#|";
            	}else if (intActiveMap[i][j]>1){
            		strReturn+="-|";
            	}else if (intActiveMap[i][j]>=0){
            		strReturn+=" |";
            	}
            	*/
            }
            strReturn+="  ";
            strReturn+=strAr1MapCoordinateChars[i]+"|";
            for(int j=0;j<intMapSize;j++) {
            	if (intAr2EventMap[i][j]!=-1){
            		strReturn+=intAr2EventMap[i][j]+"|";
            	}else{
            		strReturn+="_|";
            	}
            }
            strReturn+="  ";
            strReturn+=strAr1MapCoordinateChars[i]+"|";
            for(int j=0;j<intMapSize;j++) {
            	if (intAr2WeaponMap[i][j]!=-1){
            		strReturn+=intAr2WeaponMap[i][j]+"|";
            	}else{
            		strReturn+="_|";
            	}
            }
            strReturn+="\n";
        }
		return (strReturn);
	}
	public static int[][] generateIntMap(){
		System.out.println("Map is being generated.");
		//start at 0
		int seedY = (int) Math.floor((Math.random()*intMapSize));
		int seedX = (int) Math.floor((Math.random()*intMapSize));
		for (int y=0;y<intMapSize;y++){
			for (int x=0;x<intMapSize;x++){
				Map.intAr2RandomMap[y][x]=0;
			}
		}
		for (int i=0;i<intNumOfPeaks;i++){
			//random point
			seedY = (int) Math.floor((Math.random()*intMapSize));
			seedX = (int) Math.floor((Math.random()*intMapSize));
			//increase around
			for (int y=0;y<intMapSize;y++){
				for (int x=0;x<intMapSize;x++){
					//int distanceY = Math.abs((Map.intRandomMap[seedY][seedX] - Map.intRandomMap[y][x]));
					//int distanceX = Math.abs((Map.intRandomMap[seedY][seedX] - Map.intRandomMap[y][x]));
					int distanceY = Math.abs(seedY - y);
					int distanceX = Math.abs(seedX - x);
					//int a = (int) Math.ceil(distanceY);
					//int b = (int) Math.ceil(distanceX);
					int distance = (int) Math.floor((distanceY+distanceX)/2)+2;
					//int distance = (int) Math.floor(Math.sqrt((a^2)+(b^2)));
					if (Map.intAr2RandomMap[y][x]!=1){
						if(distance<=Map.intAr2RandomMap[y][x] || Map.intAr2RandomMap[y][x]==0){
							Map.intAr2RandomMap[y][x]=distance;
						};
					};
				};
			};
		};
		Map.intAr2RandomMap[seedY][seedX]=1;
		return Map.intAr2RandomMap;
	};
	public static void generateEventMap(){
		for (int y=0;y<intMapSize;y++){
			for (int x=0;x<intMapSize;x++){
				Map.intAr2EventMap[y][x]=-1;
			}
		}
		for (int y=0;y<intMapSize;y++){
			for (int x=0;x<intMapSize;x++){
				int intRandomEventPlacer = (int) Math.floor((Math.random()*8));				// Randomly decides whether this tile is an event or not
				if (intRandomEventPlacer==0){
					int intRandomEventTypeSelector = (int) Math.floor((Math.random()*3));   // Randomly decides whether this event is a bush or frog
					int intRandomEventSelector = (int) Math.floor((Math.random()*4));		// Randomly decides what color it is
					if (intRandomEventTypeSelector==0){
						intRandomEventSelector += 4;
					}
					intAr2EventMap[y][x]=intRandomEventSelector;
				}
			}
		}
	}
	public static void generateWeaponMap(){
		for (int y=0;y<intMapSize;y++){
			for (int x=0;x<intMapSize;x++){
				Map.intAr2WeaponMap[y][x]=-1;
			}
		}
		for (int y=0;y<intMapSize;y++){
			for (int x=0;x<intMapSize;x++){
				int intRndWeaponPlacer = (int) Math.floor((Math.random()*16));
				if (intRndWeaponPlacer==0){
					int intRndWeaponSelector = (int) Math.floor((Math.random()*11));
					intAr2WeaponMap[y][x]=intRndWeaponSelector;
				}
			}
		}
	}
}
