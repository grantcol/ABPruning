import java.util.ArrayList;
import java.util.List;

public class GameTree {

	private GameTreeNode root;
	public List<String> bestMoves = new ArrayList<String>();
	
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
		for(String n : bestMoves){
			System.out.println(n);
			return n;
		}
		return "You will lose";
	}
	public int maxValue(GameTreeNode gtn, int alpha, int beta, int depth){
		if(gtn.isEndGame()){
			if(depth == 1){
				bestMoves.add(gtn.moveDiff);
			}
			return gtn.score;
		}
		findLegalMoves(gtn);
		for(GameTreeNode n : gtn.children){
			System.out.println(n.moveDiff);
			alpha = Math.max(alpha, minValue(n,alpha, beta, depth+1));
			if(alpha >= beta){
				System.err.println("Skipping "+n.moveDiff);
				return alpha;
			}
		}
		return alpha; 
	}

	public int minValue(GameTreeNode gtn, int alpha, int beta, int depth){
		if(gtn.isEndGame()){
			return gtn.score;
		}
		findLegalMoves(gtn);
		for(GameTreeNode n : gtn.children){
			System.out.println(n.moveDiff);
			beta = Math.min(beta, maxValue(n, alpha, beta, depth+1));
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
