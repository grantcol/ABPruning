import java.util.List;

public class GameTree {

	private GameTreeNode root;
	
	public GameTree(){
		root = new GameTreeNode();
	}

	public GameTree(List<String> initConfig) {
		root = new GameTreeNode(initConfig, true, "Root");
	}

	public GameTreeNode getRoot(){
		return this.root;
	}
	public void setRootScore(int val){
		root.score = val;
	}
	public String findBestMove(){
		for(GameTreeNode n : root.children){
			if(n.score == 1){
				return n.moveDiff;
			}
		}
		return "You will lose";
	}
	public int maxValue(GameTreeNode gtn, int alpha, int beta, int depth){
		if(gtn.isEndGame()){
			//System.err.println(gtn.score);
			return gtn.score;
		}
		findLegalMoves(gtn);
		for(GameTreeNode n : gtn.children){
			System.out.println(n.moveDiff);
			alpha = Math.max(alpha, minValue(n,alpha, beta, depth+1));
			n.score = alpha;
			if(alpha >= beta){
				System.err.println("Skipping "+n.moveDiff);
				return alpha;
			}
		}
		return alpha; 
	}

	public int minValue(GameTreeNode gtn, int alpha, int beta, int depth){
		if(gtn.isEndGame()){
			//System.err.println(gtn.score);
			return gtn.score;
		}
		findLegalMoves(gtn);
		for(GameTreeNode n : gtn.children){
			System.out.println(n.moveDiff);
			beta = Math.min(beta, maxValue(n, alpha, beta, depth+1));
			n.score = beta;
			if(beta <= alpha){
				System.err.println("Skipping "+n.moveDiff);
				return beta;
			}
		}
		return beta;
	}
	public void findLegalMoves(GameTreeNode gtn){
		gtn.board.makeLegalMoves(gtn);
	}
	public void print(){
		System.out.println("Root config is: ");
		root.board.print();
		System.out.println(root.score);
	}
}
