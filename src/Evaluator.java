
public class Evaluator {
	public GameTree gt;
	int MAX;
	int MIN;
	
	public Evaluator(GameTree gt, int max, int min){
		this.gt = gt;
		this.MAX = max;
		this.MIN = min;
	}
	
	public int minmax(GameTreeNode gtn, int player){
		
		return 0;
	}
	
	public boolean winStateReached(int player, GameTreeNode gtn){
		return true;
	}
	
}
