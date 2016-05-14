package crocodile_hunter;

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
	static int[][] intAr2RandomMap = {
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

	};
	static int[][] intAr2EventMap = {
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
	};
	static int[] intAr1Height = {4,4,3,3,2,2,1,1,0,0};
	static String[] strAr1FullASCII = {" ","@","¤","+","=","~","-",",","."," "  ," "," "," "," "," "};
	//static String[] strSemiASCIIList = {" ","~","~","~","~","-","-","-","-","-"};
	static String[] strAr1SemiASCII = {" ","-","-","-","-"," "," "," "," "," "  ," "," "," "," "," "};
	//static String[] strASCIIList = {"0","1","2","3","4","5","6","7","8","9","A","B"};
	static String[] strAr1ActiveASCII=strAr1SemiASCII;
	static int intNumOfPeaks=1;
	public static int[][] intAr2ActiveMap;
	
	static int[] intAr1EventType = {0,0,1};
	static int[] intAr1EventColor = {0,1,2,3};
	static int[] intAr1EventOdd = {0,0,0,1};
	
	// buff, level, duration, deBuff, level, duration
	static int[][] intEventData = {
			{0,1,10,1,1,10},//red berries
			{2,1,10,3,1,10},//blue berries
			{4,1,10,5,1,10},//green berries
			{6,1,5,7,1,5},//yellow berries
			
			{0,2,10,1,2,10},//red frog
			{2,2,10,3,2,10},//blue frog
			{4,2,10,5,2,10},//green frog
			{6,2,5,7,2,5},//yellow frog
	};
	
	Map(){
	}
	public static String generateStrMap(Player player, Croc croc, int[][] intActiveMap, String[] strAr1ActiveASCII){
		String strReturn = "You look at your map.\n\n_|0|1|2|3|4|5|6|7|8|9|\n";
		for(int i=0;i<10;i++) {
			strReturn+=i+"|";
            for(int j=0;j<10;j++) {
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
            strReturn+="\n";
        }
		return (strReturn);
	}
	public static int[][] generateIntMap(){
		System.out.println("Map is being generated.");
		//start at 0
		int seedY = (int) Math.floor((Math.random()*10));
		int seedX = (int) Math.floor((Math.random()*10));
		for (int i=0;i<intNumOfPeaks;i++){
			//random point
			seedY = (int) Math.floor((Math.random()*10));
			seedX = (int) Math.floor((Math.random()*10));
			//increase around
			for (int y=0;y<10;y++){
				for (int x=0;x<10;x++){
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
	public static int[][] generateEventMap(){
		for (int y=0;y<10;y++){
			for (int x=0;x<10;x++){
				int intRandomEventPlacer = (int) Math.floor((Math.random()*6));
				if (intRandomEventPlacer==0){
					int intRandomEvent = (int) Math.floor((Math.random()*8));
					intAr2EventMap[y][x]=intRandomEvent;
				}
			}
		}
		return intAr2EventMap;
	}
}
