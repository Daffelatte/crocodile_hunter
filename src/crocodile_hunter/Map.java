package crocodile_hunter;

public class Map{
	static int[][] intDefaultMap = {
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
	static int[][] intRandomMap = {
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0},

	};
	static int[] intHeightList = {4,4,3,3,2,2,1,1,0,0};
	static String[] strFullASCIIList = {" ","@","¤","+","=","~","-",",","."," "  ," "," "," "," "," "};
	//static String[] strSemiASCIIList = {" ","~","~","~","~","-","-","-","-","-"};
	static String[] strSemiASCIIList = {" ","-","-","-","-"," "," "," "," "," "  ," "," "," "," "," "};
	//static String[] strASCIIList = {"0","1","2","3","4","5","6","7","8","9","A","B"};
	static String[] strActiveASCIIList=strFullASCIIList;
	static int intNumOfPeaks=1;
	public static int[][] intActiveMap;
	public Map(){
	}
	public static String generateStrMap(Player player, Crocodile crocodile, int[][] intActiveMap, String[] strActiveASCIIList){
		String strReturn = "You look at your map.\n\n_|0|1|2|3|4|5|6|7|8|9|\n";
		for(int i=0;i<10;i++) {
			strReturn+=i+"|";
            for(int j=0;j<10;j++) {
            	if (player.unitMap[i][j]){
            		strReturn+="P|";
            	}else if (crocodile.unitMap[i][j] && main.showCrocodileOnMap){
            		strReturn+="C|";
            	}else{
            		strReturn+=strActiveASCIIList[Map.intActiveMap[i][j]]+"|";
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
					if (Map.intRandomMap[y][x]!=1){
						if(distance<=Map.intRandomMap[y][x] || Map.intRandomMap[y][x]==0){
							Map.intRandomMap[y][x]=distance;
						};
					};
				};
			};
		};
		Map.intRandomMap[seedY][seedX]=1;
		return Map.intRandomMap;
	};
}
