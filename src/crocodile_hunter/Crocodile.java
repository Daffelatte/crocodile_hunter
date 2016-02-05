package crocodile_hunter;

public class Crocodile extends Unit{

	public Crocodile(int startX, int startY, int speed) {
		super(startX, startY, speed);
		// TODO Auto-generated constructor stub
	}

	public int chooseCommand() {
		int intCommand=(int) Math.floor(5 * Math.random());
		int intReturn=main.intCommandList[intCommand];
		return intReturn;
	};

}
